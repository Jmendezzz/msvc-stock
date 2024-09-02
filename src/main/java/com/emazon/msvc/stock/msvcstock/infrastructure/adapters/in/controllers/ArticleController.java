package com.emazon.msvc.stock.msvcstock.infrastructure.adapters.in.controllers;


import com.emazon.msvc.stock.msvcstock.application.dtos.article.ArticleResponseDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.article.CreateArticleRequestDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.article.ListArticleResponseDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.pagination.PaginationDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.sorting.SortingDto;
import com.emazon.msvc.stock.msvcstock.application.handlers.ArticleHandler;
import com.emazon.msvc.stock.msvcstock.domain.models.Paginated;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
@RequestMapping("api/v1/articles")
@AllArgsConstructor
@Tag(name = "Article Controller", description = "Article Management")
public class ArticleController {
  private final ArticleHandler articleHandler;
  @Operation(
          summary = "Create an article",
          description = "Create an article in the stock",
          parameters = {
                  @Parameter(
                          name = "articleDto",
                          description = "Article information",
                          required = true,
                          schema = @Schema(implementation = CreateArticleRequestDto.class)
                  )
          }
  )
  @ApiResponses(
          value = {
                  @ApiResponse(
                          responseCode = "201",
                          description = "Article created successfully"
                  ),
                  @ApiResponse(
                          responseCode = "400",
                          description = "Bad request, invalid data provided"
                  )
          }
  )
  @PostMapping("/create")
  public ResponseEntity<ArticleResponseDto> createArticle(@Valid @RequestBody CreateArticleRequestDto articleDto){
    return new ResponseEntity<>(
            articleHandler.createArticle(articleDto),
            HttpStatus.CREATED
      );
  }

  @Operation(
          summary = "Retrieve articles",
          description = "Retrieve articles from the stock",
          parameters = {
                  @Parameter(
                          name = "pagination",
                          description = "Pagination information",
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
                          description = "Articles retrieved successfully"
                  ),
                  @ApiResponse(
                          responseCode = "400",
                          description = "Bad request, invalid data provided"
                  )
          }

  )
  @GetMapping()
  public ResponseEntity<Paginated<ListArticleResponseDto>> retrieveArticles(
          @ModelAttribute PaginationDto pagination,
          @ModelAttribute SortingDto sorting
          ){
    return new ResponseEntity<>(
            articleHandler.retrieveArticles(
                    pagination,
                    sorting
            ),
            HttpStatus.OK
    );
  }
}
