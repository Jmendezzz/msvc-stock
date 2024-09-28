package com.emazon.msvc.stock.msvcstock.infrastructure.adapters.in.controllers;


import com.emazon.msvc.stock.msvcstock.application.dtos.article.*;
import com.emazon.msvc.stock.msvcstock.application.dtos.pagination.PaginationDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.searchcriteria.ArticleSearchCriteriaRequestDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.sorting.SortingDto;
import com.emazon.msvc.stock.msvcstock.application.handlers.ArticleHandler;
import com.emazon.msvc.stock.msvcstock.domain.models.Paginated;
import com.emazon.msvc.stock.msvcstock.infrastructure.exceptions.EntityNotFoundException;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.emazon.msvc.stock.msvcstock.domain.utils.constants.article.ArticleExceptionMessage.ARTICLE_NOT_FOUND;

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
  @PreAuthorize("hasRole(@adminRole)")
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
  @PostMapping("/search")
  public ResponseEntity<Paginated<ListArticleResponseDto>> retrieveArticles(
          @Valid @ModelAttribute PaginationDto pagination,
          @Valid @ModelAttribute SortingDto sorting,
          @Valid @RequestBody ArticleSearchCriteriaRequestDto articleSearchCriteria
          ){
    return new ResponseEntity<>(
            articleHandler.retrieveArticles(
                    pagination,
                    sorting,
                    articleSearchCriteria
            ),
            HttpStatus.OK
    );
  }

  @GetMapping("/{articleId}")
  public ResponseEntity<ArticleResponseDto> retrieveArticle(@PathVariable Long articleId){
    ArticleResponseDto articleResponseDto =
            articleHandler
            .retrieveArticleById(articleId)
            .orElseThrow(() ->  new EntityNotFoundException(ARTICLE_NOT_FOUND));

    return new ResponseEntity<>(articleResponseDto, HttpStatus.OK);
  }
  @GetMapping("/batch")
  public ResponseEntity<List<ArticleResponseDto>> retrieveArticlesByIds(@RequestParam List<Long> articleIds){
    return new ResponseEntity<>(
            articleHandler.retrieveArticlesByIds(articleIds),
            HttpStatus.OK
    );
  }

  @PatchMapping("/{articleId}/stock")
  @PreAuthorize("hasRole(@machineRole)")
  public ResponseEntity<Void> updateArticleStock(
          @PathVariable Long articleId,
          @Valid @RequestBody UpdateArticleStockRequestDto updateArticleStockRequestDto
          ){
    articleHandler.updateArticleStock(articleId, updateArticleStockRequestDto.quantity());
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @GetMapping("/{articleId}/exists")
  public ResponseEntity<Boolean> articleExists(@PathVariable Long articleId){
    return new ResponseEntity<>(
            articleHandler.articleExists(articleId),
            HttpStatus.OK
    );
  }
}
