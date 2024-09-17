package com.emazon.msvc.stock.msvcstock.domain.ports.in.usecases.article;

public interface UpdateArticleUseCase {
  void updateArticleStock(Long articleId, Integer quantity);
}
