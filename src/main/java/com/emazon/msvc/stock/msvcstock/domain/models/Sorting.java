package com.emazon.msvc.stock.msvcstock.domain.models;

import com.emazon.msvc.stock.msvcstock.domain.enums.SortDirection;
import com.emazon.msvc.stock.msvcstock.domain.exceptions.InvalidInputsException;
import com.emazon.msvc.stock.msvcstock.domain.exceptions.sorting.SortingExceptionCode;
import com.emazon.msvc.stock.msvcstock.domain.utils.InputValidation;

import java.util.HashMap;
import java.util.Map;


public class Sorting {
    private String sortBy;
    private SortDirection direction;

    public Sorting(String sortBy, String direction) {
        validate(sortBy, direction);
        this.sortBy = sortBy;
        this.direction = SortDirection.valueOf(direction);
    }

    private void validate(String field, String direction){
        Map<String, String> errors = new HashMap<>();
        if(InputValidation.isNullOrEmpty(field)){
            errors.put("field", SortingExceptionCode.EMPTY_SORT_BY.getMessage());
        }
        if(!SortDirection.contains(direction)){
            errors.put("direction", SortingExceptionCode.INVALID_SORT_DIRECTION.getMessage());
        }
        if(!errors.isEmpty()){
            throw new InvalidInputsException(errors);
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
