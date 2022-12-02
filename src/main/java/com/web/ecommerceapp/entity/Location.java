package com.web.ecommerceapp.entity;

import com.web.ecommerceapp.entity.embeddable.Address;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="locations")
public class Location extends BaseEntity {


    @Column(name="name")
    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy ="location",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<ProductLocation> productLocations;

    @CreationTimestamp
    @Column(name="created_at")
    private LocalDate created_at;

    @UpdateTimestamp
    @Column(name="updated_at")
    private LocalDate updated_at;

    @OneToOne
    private User user;

    @PreRemove
    public void preRemove() {
        //this.getProductLocations().clear();
        this.getUser().setLocation(null);
    }

}
