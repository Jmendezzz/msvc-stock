package com.emazon.msvc.stock.msvcstock.infrastructure.adapters.in.controllers;


import com.emazon.msvc.stock.msvcstock.application.dtos.article.ArticleDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.article.CreateArticleDto;
import com.emazon.msvc.stock.msvcstock.application.services.ArticleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
