package com.emazon.msvc.stock.msvcstock.domain.models;

import com.emazon.msvc.stock.msvcstock.domain.enums.SortDirection;
import com.emazon.msvc.stock.msvcstock.domain.exceptions.sorting.SortingExceptionCode;
import com.emazon.msvc.stock.msvcstock.domain.utils.InputValidation;
import lombok.*;

import java.util.HashMap;
import java.util.Map;


public class Sorting {
    private String sortBy;
    private SortDirection direction;

    public Sorting(String sortBy, SortDirection direction) {
        validate(sortBy, direction);
        this.sortBy = sortBy;
        this.direction = direction;
    }

    private void validate(String field, SortDirection direction){
        Map<String, String> errors = new HashMap<>();
        if(InputValidation.isNullOrEmpty(field)){
            errors.put("field", SortingExceptionCode.EMPTY_SORT_BY.getMessage());
        }
        if(!SortDirection.contains(direction.toString())){
            errors.put("direction", SortingExceptionCode.INVALID_SORT_DIRECTION.getMessage());
        }
        if(!errors.isEmpty()){
            throw new IllegalArgumentException(errors.toString());
        }
    }
    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }
    public SortDirection getDirection() {
        return direction;
    }

    public void setDirection(SortDirection direction) {
        this.direction = direction;
    }
}
