package com.web.ecommerceapp.entity;

import lombok.*;
import org.aspectj.weaver.loadtime.Agent;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="users", uniqueConstraints = {@UniqueConstraint(columnNames =
        {"username", "email"})})
public class User extends BaseEntity {

    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;

    @Column(name="username")
    private String username;
    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    @CreationTimestamp
    @Column(name="created_at")
    private LocalDate created_at;

    @UpdateTimestamp
    @Column(name="updated_at")
    private LocalDate updated_at;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name="user_role",joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
            inverseJoinColumns =@JoinColumn(name="role_id",referencedColumnName = "id") )
    private Set<Role> roles=new HashSet<>();

    @OneToOne(mappedBy = "user",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Location location;

    @ManyToOne
    @JoinColumn(name="manager_id")
    private User manager;


    @OneToMany(fetch = FetchType.LAZY,mappedBy = "operator",cascade =CascadeType.ALL)
    private List<OperatorSetting> operatorSettingOperator;

    @OneToOne(fetch = FetchType.EAGER,mappedBy ="manager",cascade = CascadeType.ALL,orphanRemoval = true)
    private OperatorSetting operatorSettingManager;
}
