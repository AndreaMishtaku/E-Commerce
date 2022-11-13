package com.web.ecommerceapp.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@Table(name="categories")
public class Category extends BaseEntity {

    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //private Long id;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @CreationTimestamp
    @Column(name="created_at")
    private LocalDate created_at;

    @UpdateTimestamp
    @Column(name="updated_at")
    private LocalDate updated_at;

}
