package com.web.ecommerceapp.entity;

import lombok.Data;

import javax.persistence.*;


@Entity
@Data
@Table(name="operator_setting")
public class OperatorSetting {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @ManyToOne
    @JoinColumn(name="operator_id")
    private User operator;

    @OneToOne
    @JoinColumn(name="manager_id")
    private User manager;

}
