package com.emazon.msvc.stock.msvcstock.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Paginated<T>{
    private List<T> data;
    private Long currentPage;
    private Long totalItems;
    private Long totalPages;
}
