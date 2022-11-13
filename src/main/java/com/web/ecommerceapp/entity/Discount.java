package com.web.ecommerceapp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Table(name="discounts")
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="describtion")
    private String description;

    @Column(name="percentage")
    private Double percentage;

    @Column(name="isActive")
    private Boolean isActive;

    @Column(name ="start_date" )
    private LocalDate start_date;

    @Column(name = "end_date")
    private LocalDate end_date;

    @Column(name="created_at")
    private LocalDate created_at;


    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL,mappedBy = "discount")
    private Set<Product> products=new HashSet<>();
}
