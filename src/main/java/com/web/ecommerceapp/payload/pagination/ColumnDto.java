package com.web.ecommerceapp.payload.pagination;

import com.web.ecommerceapp.enums.ColumnType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ColumnDto {
    private String name;
    private String field;
    private ColumnType type;
    private Boolean filterable;
    private Boolean searchable;
    private Boolean orderable;
    private Boolean hidden;
}
