package com.web.ecommerceapp.service.impl;

import com.web.ecommerceapp.entity.*;
import com.web.ecommerceapp.enums.OrderType;
import com.web.ecommerceapp.exception.ResourceNotFoundException;
import com.web.ecommerceapp.mapper.OrderMapper;
import com.web.ecommerceapp.payload.order.OrderRequestDto;
import com.web.ecommerceapp.payload.order.OrderResponseDto;
import com.web.ecommerceapp.payload.response.ActionSuccessful;
import com.web.ecommerceapp.repository.*;
import com.web.ecommerceapp.service.OrderService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private UserRepository userRepository;
    private OrderRepository orderRepository;

    private ProductRepository productRepository;

    private BankAccountRepository bankAccountRepository;

    private BankCardRepository bankCardRepository;

    private TransactionRepository transactionRepository;

    private LocationRepository locationRepository;

    private ProductLocationRepository productLocationRepository;

    private PasswordEncoder passwordEncoder;

    private OrderMapper orderMapper;

    public OrderServiceImpl(UserRepository userRepository, OrderRepository orderRepository, ProductRepository productRepository, BankAccountRepository bankAccountRepository, BankCardRepository bankCardRepository, TransactionRepository transactionRepository, LocationRepository locationRepository, PasswordEncoder passwordEncoder,ProductLocationRepository productLocationRepository, OrderMapper orderMapper) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.bankAccountRepository = bankAccountRepository;
        this.bankCardRepository = bankCardRepository;
        this.transactionRepository = transactionRepository;
        this.locationRepository = locationRepository;
        this.passwordEncoder = passwordEncoder;
        this.productLocationRepository=productLocationRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    public ActionSuccessful createOrder(OrderRequestDto orderDto, Principal principal) {
        User user=principal!=null?userRepository.getUserByEmailOrUsername(principal.getName(), principal.getName()):null;


        BankCard bankCard=bankCardRepository.findByCode(orderDto.getBankCard().getCode()).orElseThrow(()->new ResourceNotFoundException("BankAccount","code",orderDto.getBankCard().getCode()));
        if(!passwordEncoder.matches(orderDto.getBankCard().getPassword(),bankCard.getPassword())){
            throw new RuntimeException("Paswordi i vendosur gabim");
        }

        BankAccount bankAccount=bankCard.getBankAccount();


        Order order=new Order();
        List<OrderProduct> orderProducts=orderDto.getOrderProducts().stream().map((op)->{
            Product product=productRepository.findById(op.getProductId()).orElseThrow(()->new ResourceNotFoundException("Product","id",op.getProductId()));
            OrderProduct orderProduct=new OrderProduct();
            orderProduct.setOrder(order);
            orderProduct.setQuantity(op.getQuantity());
            orderProduct.setProduct(product);
            if(user==null){
                Location l=locationRepository.getLocationByProductStockOrder(op.getProductId()).get(0);
                ProductLocation pl=productLocationRepository.findByLocation_idAndProduct_id(l.getId(),op.getProductId());
                if(pl.getStock()<op.getQuantity()){
                    throw new RuntimeException("Blerja nuk mund te kryhet");
                }else if(pl.getStock()==op.getQuantity()){
                    l.getProductLocations().remove(pl);
                    locationRepository.save(l);
                }else{
                    for (ProductLocation temp:l.getProductLocations()) {
                        if(temp.equals(pl)){
                            temp.setStock(pl.getStock()-op.getQuantity());
                        }
                    }
                }

            }else{
                Location l=user.getLocation();
                ProductLocation pl=productLocationRepository.findByLocation_idAndProduct_id(l.getId(),op.getProductId());
                if(pl.getStock()<op.getQuantity()){
                    throw new RuntimeException("Blerja nuk mund te kryhet");
                }else if(pl.getStock()==op.getQuantity()){
                    l.getProductLocations().remove(pl);
                }else{
                    for (ProductLocation temp:l.getProductLocations()) {
                        if(temp.equals(pl)){
                            temp.setStock(pl.getStock()-op.getQuantity());
                        }
                    }
                }
                locationRepository.save(l);
            }
            return orderProduct;
        }).collect(Collectors.toList());
        order.setDescription(orderDto.getDescription());
        order.setBankCard(bankCard);
        order.setOrderProducts(orderProducts);
        order.setType(principal==null?OrderType.ONLINE:OrderType.PHYSICAL);
        order.setAmount(order.getAmount());
        order.setUser(user);
        order.setLocation(user==null?null:user.getLocation());


        Transaction transaction=new Transaction();
        transaction.setBankCard(bankCard);
        transaction.setAmount(order.getAmount());
        transaction.setDescription(orderDto.getDescription());
        transaction.setDate(LocalDateTime.now());

        transactionRepository.save(transaction);

        if(bankAccount.getBalance()<=order.getAmount()){
            throw new RuntimeException("Ju nuk keni mjaftueshem para per te kryer porosine");
        }
        bankAccount.setBalance(bankAccount.getBalance()-order.getAmount());

        orderRepository.save(order);
        bankAccountRepository.save(bankAccount);

        return new ActionSuccessful(true,"Order created successfully");
    }

    @Override
    public List<OrderResponseDto> getAllOrdersOfOperator(Principal principal) {

        User user=userRepository.getUserByEmailOrUsername(principal.getName(), principal.getName());

        List<Order> orders=orderRepository.getOrdersByUserId(user.getId());

        List< OrderResponseDto> orderList=orderMapper.orderToDto(orders);

        return orderList;
    }

    @Override
    public List<OrderResponseDto> getAllOrders() {
        List<Order> orders=orderRepository.findAll();

        List<OrderResponseDto> orderList=orderMapper.orderToDto(orders);

        return orderList;
    }



    @Override
    public OrderResponseDto getOrderById(Long id,Principal principal) {
        User user=userRepository.getUserByEmailOrUsername(principal.getName(), principal.getName());
        Order order=orderRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Order","id",id));

        if(!canAccessOrderById(user,order)){
            throw new RuntimeException("Nuk keni akses per kete order");
        }

        OrderResponseDto orderResponse=orderMapper.orderToDto(order);
        return orderResponse;
    }

    @Override
    public ActionSuccessful deleteOrderById(Long id, Principal principal) {
        User user=userRepository.getUserByEmailOrUsername(principal.getName(), principal.getName());
        Order order=orderRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Order","id",id));

        if(user.getId()!=order.getUser().getId()){
            throw new RuntimeException("Nuk keni akses per kete id");
        }

        orderRepository.delete(order);
        return new ActionSuccessful(true,"Order deleted successfully");
    }

    @Override
    public List<OrderResponseDto> getAllOnlineOrders() {
        List<Order> orders=orderRepository.getOnlineOrders();

        List<OrderResponseDto> orderList=orderMapper.orderToDto(orders);

        return null;
    }

    private boolean canAccessOrderById(User user,Order order){
        User orderUser=order.getUser();
        if(orderUser!=null) {
            if(orderUser.getId()==user.getId()){
                return true;
            }
            for (OperatorSetting o:
                    user.getOperatorSettingOperator()
                 ) {

                if(o.getManager().getId()==order.getUser().getId()){
                    return true;
                }

            }

        }
        return false;
    }
}
