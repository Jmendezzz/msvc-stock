package com.emazon.msvc.stock.msvcstock.domain.models;

import com.emazon.msvc.stock.msvcstock.domain.enums.SortDirection;
import com.emazon.msvc.stock.msvcstock.domain.exceptions.InvalidInputException;
import com.emazon.msvc.stock.msvcstock.domain.utils.InputValidation;
import com.emazon.msvc.stock.msvcstock.domain.utils.constants.sorting.SortingConstant;

import static com.emazon.msvc.stock.msvcstock.domain.utils.constants.sorting.SortingValidationCode.SORTING_INVALID_DIRECTION;
import static com.emazon.msvc.stock.msvcstock.domain.utils.constants.sorting.SortingValidationMessage.SORTING_INVALID_DIRECTION_CODE;

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
        this.sortBy = sortBy;
    }
    public SortDirection getDirection() {
        return direction;
    }

    public void setDirection(String direction) {

        if(InputValidation.isNullOrEmpty(direction)){
            this.direction = SortingConstant.SORTING_DEFAULT_DIRECTION;
        }else{
            if(!SortDirection.contains(direction)){
                throw new InvalidInputException(SORTING_INVALID_DIRECTION, SORTING_INVALID_DIRECTION_CODE);
            }
            this.direction = SortDirection.valueOf(direction);
        }
    }
}
