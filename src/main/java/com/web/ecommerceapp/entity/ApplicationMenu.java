package com.web.ecommerceapp.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Menu")
public class ApplicationMenu extends BaseEntity{
    @Column(name="name")
    private String name;

    @Column(name="route")
    private String route;

    @Column(name="icon")
    private String icon;

    @Column(name="collapsible")
    private Boolean collapsible;

    @Column(name="parentId")
    private Long ParentId;

    @OneToOne
    @JoinColumn(name="roleId")
    private Role role;
}
