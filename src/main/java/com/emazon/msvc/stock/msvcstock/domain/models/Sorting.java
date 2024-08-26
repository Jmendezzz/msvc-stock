package com.emazon.msvc.stock.msvcstock.domain.models;

import com.emazon.msvc.stock.msvcstock.domain.enums.SortDirection;
import com.emazon.msvc.stock.msvcstock.domain.exceptions.InvalidInputException;
import com.emazon.msvc.stock.msvcstock.domain.exceptions.sorting.SortingExceptionCode;
import com.emazon.msvc.stock.msvcstock.domain.utils.InputValidation;

public class Sorting {
    private String sortBy;
    private SortDirection direction;

    public Sorting(String sortBy, String direction) {
        setSortBy(sortBy);
        setDirection(direction);
    }
    public Sorting(){
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        if(InputValidation.isNullOrEmpty(sortBy)){
            throw new InvalidInputException(SortingExceptionCode.EMPTY_SORT_BY.getMessage(), SortingExceptionCode.EMPTY_SORT_BY.getCode());
        }
        this.sortBy = sortBy;
    }
    public SortDirection getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        if(!SortDirection.contains(direction)){
            throw new InvalidInputException(SortingExceptionCode.INVALID_SORT_DIRECTION.getMessage(), SortingExceptionCode.INVALID_SORT_DIRECTION.getCode());
        }
        this.direction = SortDirection.valueOf(direction);
    }
}
