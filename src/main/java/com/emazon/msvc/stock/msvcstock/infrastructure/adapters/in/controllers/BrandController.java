package com.emazon.msvc.stock.msvcstock.infrastructure.adapters.in.controllers;

import com.emazon.msvc.stock.msvcstock.application.dtos.brand.BrandDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.brand.CreateBrandDto;
import com.emazon.msvc.stock.msvcstock.application.services.BrandService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/brands")
@AllArgsConstructor
public class BrandController {
  private final BrandService brandService;

  @PostMapping("/create")
  public ResponseEntity<BrandDto> createBrand(@Valid @RequestBody CreateBrandDto createBrandDto) {
    return ResponseEntity.ok(brandService.createBrand(createBrandDto));
  }
}
