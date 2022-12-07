package com.web.ecommerceapp.payload.menu;

import lombok.Data;

import java.util.List;


@Data
public class MenuDto extends BaseMenuDto{
    public List<BaseMenuDto> children;
}
