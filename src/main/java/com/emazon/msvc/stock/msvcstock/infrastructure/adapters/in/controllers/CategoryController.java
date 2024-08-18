package com.emazon.msvc.stock.msvcstock.infrastructure.adapters.in.controllers;

import com.emazon.msvc.stock.msvcstock.application.dtos.category.CategoryDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.category.CreateCategoryDto;
import com.emazon.msvc.stock.msvcstock.application.services.imp.CategoryService;
import com.emazon.msvc.stock.msvcstock.domain.enums.SortDirection;
import com.emazon.msvc.stock.msvcstock.domain.models.Paginated;
import com.emazon.msvc.stock.msvcstock.domain.models.Pagination;
import com.emazon.msvc.stock.msvcstock.domain.models.Sorting;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/categories")
@AllArgsConstructor
public class CategoryController {
  private final CategoryService categoryService;

  @GetMapping
  public ResponseEntity<Paginated<CategoryDto>> retrieveCategories(
          @RequestParam(defaultValue = "0") int page,
          @RequestParam(defaultValue = "20") int size,
          @RequestParam(defaultValue = "name") String sortBy,
          @RequestParam(defaultValue = "asc") String direction) {

    SortDirection sortDirection = Sort.Direction.fromString(direction).equals(Sort.Direction.ASC)
            ? SortDirection.ASC
            : SortDirection.DESC;

    Sorting sorting = new Sorting(sortBy, sortDirection);
    Pagination pagination = new Pagination(page, size);
    Paginated<CategoryDto> paginatedCategories = categoryService.retrieveCategories(pagination, sorting);

    return new ResponseEntity<>(paginatedCategories, HttpStatus.OK);
  }
  @PostMapping("/create")
  public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CreateCategoryDto createCategoryDto) {
    return new ResponseEntity<>(
            categoryService.create(createCategoryDto),
            HttpStatus.CREATED
    );
  }

}
