package com.web.ecommerceapp.entity;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="bank_cards",uniqueConstraints=@UniqueConstraint(columnNames={"code"}))
public class BankCard extends BaseEntity{

    @Column(name="code")
    private String code;

    @Column(name="password")
    private String password;

    @OneToOne
    @JoinColumn(name="bankAccount_id")
    private BankAccount bankAccount;

    @Column(name = "expiry_date")
    private LocalDate expiryDate;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDate created_at;

    @UpdateTimestamp
    @Column(name="updated_at")
    private LocalDate updated_at;
}
