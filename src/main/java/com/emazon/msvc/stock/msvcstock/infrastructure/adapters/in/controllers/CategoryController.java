package com.emazon.msvc.stock.msvcstock.infrastructure.adapters.in.controllers;

import com.emazon.msvc.stock.msvcstock.application.dtos.category.CategoryDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.category.CreateCategoryDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.pagination.PaginationDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.sorting.SortingDto;
import com.emazon.msvc.stock.msvcstock.application.services.imp.CategoryService;
import com.emazon.msvc.stock.msvcstock.domain.models.Paginated;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
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
          @Valid @ModelAttribute PaginationDto pagination,
          @Valid @ModelAttribute SortingDto sorting
          ) {

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
