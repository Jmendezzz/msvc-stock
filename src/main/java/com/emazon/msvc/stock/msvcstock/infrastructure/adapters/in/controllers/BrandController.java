package com.emazon.msvc.stock.msvcstock.infrastructure.adapters.in.controllers;

import com.emazon.msvc.stock.msvcstock.application.dtos.brand.BrandDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.brand.CreateBrandDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.pagination.PaginationDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.sorting.SortingDto;
import com.emazon.msvc.stock.msvcstock.application.services.BrandService;
import com.emazon.msvc.stock.msvcstock.domain.models.Paginated;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/brands")
@AllArgsConstructor
public class BrandController {
  private final BrandService brandService;

  @PostMapping("/create")
  public ResponseEntity<BrandDto> createBrand(@RequestBody CreateBrandDto createBrandDto) {
    return ResponseEntity.ok(brandService.createBrand(createBrandDto));
  }

  @GetMapping
  public ResponseEntity<Paginated<BrandDto>> retrieveBrands(
          @ModelAttribute PaginationDto pagination,
          @ModelAttribute SortingDto sorting
  ) {
    return ResponseEntity.ok(brandService.retrieveBrands(pagination,sorting));
  }
}
