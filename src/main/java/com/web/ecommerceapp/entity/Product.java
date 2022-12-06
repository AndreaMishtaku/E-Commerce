package com.web.ecommerceapp.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name="products")
public class Product extends BaseEntity{

    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //private Long id;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="productImage")
    private String productImage;
    @Column(name="price")
    private Double price;

    @ManyToOne(fetch = FetchType.EAGER,cascade =CascadeType.ALL)
    @JoinColumn(name = "category_id",referencedColumnName = "id")
    private Category category;


    @ManyToOne
    @JoinColumn(name="discount_id",referencedColumnName = "id")
    private Discount discount;


    @OneToMany(mappedBy = "product")
    private Set<ProductLocation> productLocations;

    @CreationTimestamp
    @Column(name="created_at")
    private LocalDateTime created_at;
    @UpdateTimestamp
    @Column(name="updated_at")
    private LocalDateTime updated_at;

}
