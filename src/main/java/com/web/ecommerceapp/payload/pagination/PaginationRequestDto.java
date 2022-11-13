package com.web.ecommerceapp.payload.pagination;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PaginationRequestDto {
    private Integer page;
    private Integer size;
    private List<FilterDto> filters;
    private List<SortingDto> sorting;
    //private String searchValue;


    public List<FilterDto> getFilters() {
        if (Objects.isNull(this.filters)) return new ArrayList<>();
        return this.filters;
    }

    public List<SortingDto> getSorting() {
        if (Objects.isNull(this.sorting)) return new ArrayList<>();
        return this.sorting;
    }
}
