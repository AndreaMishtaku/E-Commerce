package com.web.ecommerceapp.payload.pagination;

import com.web.ecommerceapp.enums.SortDirection;
import lombok.Data;

@Data
public class SortingDto {
    private String columName;
    private SortDirection direction;
}
