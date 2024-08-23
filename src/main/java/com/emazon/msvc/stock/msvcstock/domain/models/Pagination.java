package com.emazon.msvc.stock.msvcstock.domain.models;

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
        validate(page,size);
        this.page = page;
        this.size = size;
    }

    private void validate(int page, int size) {
        Map<String, String> errors = new HashMap<>();
        if(page < 0){
            errors.put("page", PaginationExceptionCode.INVALID_PAGE_NUMBER.getMessage());
        }
        if(size <= 0){
            errors.put("size", PaginationExceptionCode.INVALID_PAGE_SIZE.getMessage());
        }

        if(!errors.isEmpty()){
            throw new IllegalArgumentException(errors.toString());
        }
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
