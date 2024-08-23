package com.emazon.msvc.stock.msvcstock.infrastructure.adapters.in.controllers;

import com.emazon.msvc.stock.msvcstock.application.dtos.category.CategoryDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.category.CreateCategoryDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.pagination.PaginationDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.sorting.SortingDto;
import com.emazon.msvc.stock.msvcstock.application.services.CategoryService;
import com.emazon.msvc.stock.msvcstock.domain.models.Paginated;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/categories")
@AllArgsConstructor
@Tag(name = "Category Controller", description = "Category Management")
public class CategoryController {
  private final CategoryService categoryService;

  @Operation(
          summary = "Create a new category",
          description = "Create a new category with the given information",
          requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                  description = "Category information, name and description",
                  required = true,
                  content = @Content(
                          schema = @Schema(implementation = CreateCategoryDto.class)
                  )
          )
  )
  @ApiResponses(
          value = {
                  @ApiResponse(
                          responseCode = "201",
                          description = "Category created successfully",
                          content = @Content(
                                  schema = @Schema(implementation = CategoryDto.class)
                          )
                  ),
                  @ApiResponse(
                          responseCode = "400",
                          description = "Duplicated category name"
                  )
          }
  )
  @PostMapping("/create")
  public ResponseEntity<CategoryDto> createCategory(@RequestBody CreateCategoryDto createCategoryDto) {
    return new ResponseEntity<>(
            categoryService.create(createCategoryDto),
            HttpStatus.CREATED
    );
  }

  @Operation(
          summary = "Retrieve all categories with pagination and sorting",
          description = "Retrieve all categories with pagination and sorting",
          parameters = {
                  @Parameter(
                          name = "pagination",
                          description = "Pagination information",
                          required = true,
                          schema = @Schema(implementation = PaginationDto.class)
                  ),
                  @Parameter(
                          name = "sorting",
                          description = "Sorting information",
                          schema = @Schema(implementation = SortingDto.class)
                  )

          }
  )
  @ApiResponses(
          value = {
                  @ApiResponse(
                          responseCode = "200",
                          description = "Categories retrieved successfully",
                          content = @Content(
                                  schema = @Schema(implementation = Paginated.class)
                          )
                  ),
                  @ApiResponse(
                          responseCode = "400",
                          description = "Invalid pagination or sorting"
                  )
          }

  )
  @GetMapping
  public ResponseEntity<Paginated<CategoryDto>> retrieveCategories(
          @Valid @ModelAttribute PaginationDto pagination,
          @Valid @ModelAttribute SortingDto sorting
  ) {

    Paginated<CategoryDto> paginatedCategories = categoryService.retrieveCategories(pagination, sorting);

    return new ResponseEntity<>(paginatedCategories, HttpStatus.OK);
  }

}
