package com.web.ecommerceapp.entity;


import com.web.ecommerceapp.entity.embeddable.Client;
import lombok.*;


import javax.persistence.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="bank_accounts")
public class BankAccount extends BaseEntity{

    @Column(name="name")
    private String name;

    @Embedded
    private Client Client;

    @Column(name="balance")
    private Double balance;

    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL,mappedBy = "bankAccount")
    private BankCard card;
}
