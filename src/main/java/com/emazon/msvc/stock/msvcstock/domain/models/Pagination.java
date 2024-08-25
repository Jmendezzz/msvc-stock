package com.emazon.msvc.stock.msvcstock.domain.models;

import com.emazon.msvc.stock.msvcstock.domain.exceptions.InvalidInputException;
import com.emazon.msvc.stock.msvcstock.domain.exceptions.pagination.PaginationExceptionCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;


public class Pagination {
    private Integer page;
    private Integer size;


    public Pagination(int page, int size) {
        setPage(page);
        setSize(size);
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        if(page < 0){
            throw new InvalidInputException(PaginationExceptionCode.INVALID_PAGE_NUMBER.getMessage(), PaginationExceptionCode.INVALID_PAGE_NUMBER.getCode());
        }
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        if(size <= 0){
            throw new InvalidInputException(PaginationExceptionCode.INVALID_PAGE_SIZE.getMessage(), PaginationExceptionCode.INVALID_PAGE_SIZE.getCode());
        }
        this.size = size;
    }
}
