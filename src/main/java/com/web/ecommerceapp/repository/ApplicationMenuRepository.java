package com.web.ecommerceapp.repository;

import com.web.ecommerceapp.entity.ApplicationMenu;
import com.web.ecommerceapp.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface ApplicationMenuRepository extends JpaRepository<ApplicationMenu,Long> {

    List<ApplicationMenu> getApplicationMenuByRoleIn(Set<Role> roles);
}
