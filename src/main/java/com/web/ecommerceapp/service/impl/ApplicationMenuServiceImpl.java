package com.web.ecommerceapp.service.impl;

import com.web.ecommerceapp.entity.ApplicationMenu;
import com.web.ecommerceapp.entity.User;
import com.web.ecommerceapp.mapper.ApplicationMenuMapper;
import com.web.ecommerceapp.payload.menu.BaseMenuDto;
import com.web.ecommerceapp.payload.menu.MenuDto;
import com.web.ecommerceapp.repository.ApplicationMenuRepository;
import com.web.ecommerceapp.repository.UserRepository;
import com.web.ecommerceapp.service.ApplicationMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicationMenuServiceImpl implements ApplicationMenuService {

    private ApplicationMenuRepository applicationMenuRepository;
    private UserRepository userRepository;
    private ApplicationMenuMapper menuMapper;

    public ApplicationMenuServiceImpl(ApplicationMenuRepository applicationMenuRepository, UserRepository userRepository, ApplicationMenuMapper menuMapper) {
        this.applicationMenuRepository = applicationMenuRepository;
        this.userRepository = userRepository;
        this.menuMapper = menuMapper;
    }

    @Override
    public List<MenuDto> getApplicationMenu(Principal principal) {
        User user=userRepository.getUserByEmailOrUsername(principal.getName(),principal.getName());

        List<ApplicationMenu> applicationMenuList=applicationMenuRepository.getApplicationMenuByRoleIn(user.getRoles());

        List<MenuDto> menuDtoList=new ArrayList<>();

        for (ApplicationMenu a:applicationMenuList) {
            if(a.getId()==null){
                MenuDto menuDto=new MenuDto();
                menuDto.setId(a.getId());
                menuDto.setName(a.getName());
                menuDto.setRoute(a.getRoute());
                menuDto.setIcon(a.getIcon());
                menuDto.setCollapsible(a.getCollapsible());
                menuDto.setChildren(applicationMenuList.stream().filter(t->t.getParentId()==a.getId()).map((t)->{

                    BaseMenuDto b=menuMapper.menuToBaseMenuDto(t);
                    return b;
                }).collect(Collectors.toList()));
                menuDtoList.add(menuDto);
            }


        }
        return menuDtoList;
    }
}
