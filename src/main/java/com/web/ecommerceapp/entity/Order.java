package com.web.ecommerceapp.entity;

import com.web.ecommerceapp.enums.OrderType;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Data
@Table(name="orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "description")
    private String description;
    @Column(name="amount")
    private Double amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderType type;

    @CreationTimestamp
    @Column(name="created_at")
    private LocalDateTime created_at;

    @UpdateTimestamp
    @Column(name="updated_at")
    private LocalDateTime updated_at;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<OrderProduct> orderProducts;


    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id")
    private Location location;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name="bankCard_id")
    private BankCard bankCard;


    @Transient
    public Double getAmount() {
        double sum = 0D;
        List<OrderProduct> orderProducts = getOrderProducts();
        for (OrderProduct op : orderProducts) {
            sum += op.getTotalPrice();
        }
        return sum;
    }

}
