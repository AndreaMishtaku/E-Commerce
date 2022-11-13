package com.web.ecommerceapp.payload.pagination;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaginationResponseDto<T> {
    private List<ColumnDto> columns = new ArrayList<>();
    private List<T> rows;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean hasNext;
    private boolean hasPrevious;

    public PaginationResponseDto(List<T> rows, Page page,List<ColumnDto> columns) {
        this.rows = rows;
        this.columns = columns;
        this.pageNo = page.getNumber();
        this.pageSize = page.getSize();
        this.totalPages = page.getTotalPages();
        this.totalElements = page.getTotalElements();
        this.hasNext = page.hasNext();
        this.hasPrevious = page.hasPrevious();
    }
}

