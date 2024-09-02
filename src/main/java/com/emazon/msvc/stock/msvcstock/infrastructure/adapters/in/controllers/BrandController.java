package com.emazon.msvc.stock.msvcstock.infrastructure.adapters.in.controllers;

import com.emazon.msvc.stock.msvcstock.application.dtos.brand.BrandResponseDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.brand.CreateBrandRequestDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.pagination.PaginationDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.sorting.SortingDto;
import com.emazon.msvc.stock.msvcstock.application.handlers.BrandHandler;
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
@RequestMapping("/api/v1/brands")
@AllArgsConstructor
@Tag(name = "Brand Controller", description = "Brand Management")
public class BrandController {
  private final BrandHandler brandHandler;

  @PostMapping("/create")
  @Operation(
          summary = "Create a new brand",
          description = "Create a new brand with the given information",
          requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                  description = "Brand information, name and description",
                  required = true,
                  content = @Content(
                          schema = @Schema(implementation = CreateBrandRequestDto.class)
                  )
          )
  )
  @ApiResponses(
          value = {
                  @ApiResponse(
                          responseCode = "201",
                          description = "Brand created successfully",
                          content = @Content(
                                  schema = @Schema(implementation = BrandResponseDto.class)
                          )
                  ),
                  @ApiResponse(
                          responseCode = "400",
                          description = "Duplicated brand name"
                  )
          }
  )
  public ResponseEntity<BrandResponseDto> createBrand(@Valid @RequestBody CreateBrandRequestDto createBrandRequestDto) {
    return new ResponseEntity<>(
            brandHandler.createBrand(createBrandRequestDto),
            HttpStatus.CREATED
    );
  }

  @Operation(
          summary = "Retrieve all brands with pagination and sorting",
          description = "Retrieve all brands with pagination and sorting",
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
                          description = "Brands retrieved successfully",
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
  public ResponseEntity<Paginated<BrandResponseDto>> retrieveBrands(
          @ModelAttribute PaginationDto pagination,
          @ModelAttribute SortingDto sorting
  ) {
    return ResponseEntity.ok(brandHandler.retrieveBrands(pagination,sorting));
  }
}
