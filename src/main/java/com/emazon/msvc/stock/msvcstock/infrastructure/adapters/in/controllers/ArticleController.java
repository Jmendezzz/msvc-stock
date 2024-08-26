package com.emazon.msvc.stock.msvcstock.infrastructure.adapters.in.controllers;


import com.emazon.msvc.stock.msvcstock.application.dtos.article.ArticleDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.article.CreateArticleDto;
import com.emazon.msvc.stock.msvcstock.application.services.ArticleService;
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
public class ArticleController {
  private final ArticleService articleService;

  @PostMapping("/create")
  public ResponseEntity<ArticleDto> createArticle(@RequestBody CreateArticleDto articleDto){
    return new ResponseEntity<>(
            articleService.createArticle(articleDto),
            HttpStatus.CREATED
      );
  }
}
