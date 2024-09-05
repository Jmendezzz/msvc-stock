package com.emazon.msvc.stock.msvcstock.domain.models;

import com.emazon.msvc.stock.msvcstock.domain.exceptions.InvalidInputException;

import static com.emazon.msvc.stock.msvcstock.domain.utils.constants.pagination.PaginationConstant.*;
import static com.emazon.msvc.stock.msvcstock.domain.utils.constants.pagination.PaginationValidationCode.*;
import static com.emazon.msvc.stock.msvcstock.domain.utils.constants.pagination.PaginationValidationConstant.*;
import static com.emazon.msvc.stock.msvcstock.domain.utils.constants.pagination.PaginationValidationMessage.*;


public class Pagination {
    private Integer page;
    private Integer size;


    public Pagination(int page, int size) {
        setPage(page);
        setSize(size);
    }

    public Pagination(){
    }

    public int getPage() {
        return page;
    }

    public void setPage(Integer page) {
        if(page == null){
            this.page = DEFAULT_PAGE;
        }else{
            if(page < MIN_PAGE) throw new InvalidInputException(INVALID_PAGE_NUMBER, PAGE_NUMBER_INVALID_CODE);

            this.page = page;
        }
    }

    public int getSize() {
        return size;
    }

    public void setSize(Integer size) {
        if(size == null){
            this.size = DEFAULT_SIZE;
        }else{
            if(size < MIN_PAGE_SIZE || size > MAX_PAGE_SIZE) throw new InvalidInputException(INVALID_PAGE_SIZE, PAGE_SIZE_INVALID_CODE);

            this.size = size;
        }
    }
}
