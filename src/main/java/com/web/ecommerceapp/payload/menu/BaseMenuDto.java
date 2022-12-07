package com.web.ecommerceapp.payload.menu;

import lombok.Data;

@Data
public class BaseMenuDto {
    private Long id;
    private String name;
    private String route;
    private String icon;
    private Boolean collapsible;
}
