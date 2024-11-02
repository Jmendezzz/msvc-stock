package com.emazon.msvc.stock.msvcstock.application.handlers;

import com.emazon.msvc.stock.msvcstock.application.dtos.brand.BrandResponseDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.brand.CreateBrandRequestDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.pagination.PaginationDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.sorting.SortingDto;
import com.emazon.msvc.stock.msvcstock.domain.models.Paginated;

import java.util.List;

public interface BrandHandler {
  BrandResponseDto createBrand(CreateBrandRequestDto createBrandRequestDto);
  Paginated<BrandResponseDto> retrieveBrands(PaginationDto paginationDto, SortingDto sortingDto);
  List<BrandResponseDto> retrieveAllBrands();
}
