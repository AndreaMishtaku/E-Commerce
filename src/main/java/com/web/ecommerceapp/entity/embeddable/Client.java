package com.web.ecommerceapp.entity.embeddable;


import lombok.Data;

import javax.persistence.Embeddable;

@Embeddable
@Data
public class Client {
    private String firstName;
    private String lastName;
    private String age;
    private String email;
}
