package com.emazon.msvc.stock.msvcstock.domain.models;

import com.emazon.msvc.stock.msvcstock.domain.exceptions.InvalidInputException;
import com.emazon.msvc.stock.msvcstock.domain.exceptions.pagination.PaginationExceptionCode;
import com.emazon.msvc.stock.msvcstock.domain.utils.InputValidation;


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
        if(InputValidation.isNull(page)){
            throw new InvalidInputException(PaginationExceptionCode.NULL_PAGE_NUMBER.getMessage(), PaginationExceptionCode.NULL_PAGE_NUMBER.getCode());
        }
        if(page < 0){
            throw new InvalidInputException(PaginationExceptionCode.INVALID_PAGE_NUMBER.getMessage(), PaginationExceptionCode.INVALID_PAGE_NUMBER.getCode());
        }
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(Integer size) {
        if(InputValidation.isNull(size)){
            throw new InvalidInputException(PaginationExceptionCode.NULL_PAGE_SIZE.getMessage(), PaginationExceptionCode.NULL_PAGE_SIZE.getCode());
        }
        if(size <= 0){
            throw new InvalidInputException(PaginationExceptionCode.INVALID_PAGE_SIZE.getMessage(), PaginationExceptionCode.INVALID_PAGE_SIZE.getCode());
        }
        this.size = size;
    }
}
