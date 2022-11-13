package com.web.ecommerceapp.entity;


import javax.persistence.*;

@Entity
public class ManagerLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name="manager_id")
    private User manager;


    @OneToOne
    @JoinColumn(name="location_id")
    private Location location;
}
