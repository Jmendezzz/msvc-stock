package com.emazon.msvc.stock.msvcstock.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Pagination {
    private int page;
    private int size;
}
