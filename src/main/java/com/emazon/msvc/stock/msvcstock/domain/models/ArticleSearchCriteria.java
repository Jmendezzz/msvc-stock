package com.emazon.msvc.stock.msvcstock.domain.models;

import java.util.List;

public class ArticleSearchCriteria {
  private List<Long> articleIds;
  private String articleName;
  private Long categoryId;
  private Long brandId;

  public ArticleSearchCriteria(List<Long> articleIds, String articleName, Long categoryId, Long brandId) {
    this.articleIds = articleIds;
    this.articleName = articleName;
    this.categoryId = categoryId;
    this.brandId = brandId;
  }
  public ArticleSearchCriteria() {
  }

  public List<Long> getArticleIds() {
    return articleIds;
  }

  public void setArticleIds(List<Long> articleIds) {
    this.articleIds = articleIds;
  }

  public String getArticleName() {
    return articleName;
  }

  public void setArticleName(String articleName) {
    this.articleName = articleName;
  }

  public Long getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(Long categoryId) {
    this.categoryId = categoryId;
  }

  public Long getBrandId() {
    return brandId;
  }

  public void setBrandId(Long brandId) {
    this.brandId = brandId;
  }
}
