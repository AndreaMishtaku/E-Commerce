package com.web.ecommerceapp.entity;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="transactions")
public class Transaction extends BaseEntity {
    @Column(name="describtion")
    private String description;

    @Column(name="amount")
    private Double amount;

    @ManyToOne
    @JoinColumn(name="bankCard_id")
    private BankCard bankCard;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime date;
}
