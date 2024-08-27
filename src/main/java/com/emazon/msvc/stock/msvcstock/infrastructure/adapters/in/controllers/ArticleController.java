package com.emazon.msvc.stock.msvcstock.infrastructure.adapters.in.controllers;


import com.emazon.msvc.stock.msvcstock.application.dtos.article.ArticleDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.article.CreateArticleDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.article.ListArticleDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.pagination.PaginationDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.sorting.SortingDto;
import com.emazon.msvc.stock.msvcstock.application.services.ArticleService;
import com.emazon.msvc.stock.msvcstock.domain.models.Paginated;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/v1/articles")
@AllArgsConstructor
@Tag(name = "Article Controller", description = "Article Management")
public class ArticleController {
  private final ArticleService articleService;
  @Operation(
          summary = "Create an article",
          description = "Create an article in the stock"
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
  public ResponseEntity<ArticleDto> createArticle(@RequestBody CreateArticleDto articleDto){
    return new ResponseEntity<>(
            articleService.createArticle(articleDto),
            HttpStatus.CREATED
      );
  }

  @GetMapping()
  public ResponseEntity<Paginated<ListArticleDto>> retrieveArticles(
          @ModelAttribute PaginationDto pagination,
          @ModelAttribute SortingDto sorting
          ){
    return new ResponseEntity<>(
            articleService.retrieveArticles(
                    pagination,
                    sorting
            ),
            HttpStatus.OK
    );
  }
}
