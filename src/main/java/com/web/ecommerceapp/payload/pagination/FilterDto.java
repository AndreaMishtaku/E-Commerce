package com.web.ecommerceapp.payload.pagination;

import com.web.ecommerceapp.enums.Operator;
import lombok.Data;


@Data
public class FilterDto {
    private String columnName;
    private String value;
    private Operator operator;
}
