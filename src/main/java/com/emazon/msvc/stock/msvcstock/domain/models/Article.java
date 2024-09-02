package com.emazon.msvc.stock.msvcstock.domain.models;

import com.emazon.msvc.stock.msvcstock.domain.exceptions.InvalidInputException;
import com.emazon.msvc.stock.msvcstock.domain.exceptions.article.ArticleExceptionCode;
import com.emazon.msvc.stock.msvcstock.domain.utils.InputValidation;
import com.emazon.msvc.stock.msvcstock.domain.utils.constants.article.ArticleValidationCode;
import com.emazon.msvc.stock.msvcstock.domain.utils.constants.article.ArticleValidationMessage;

import java.util.List;
import java.util.Set;

public class Article {
  private Long id;
  private String name;
  private String description;
  private Double price;
  private Integer stock;
  private Brand brand;
  private Set<Category> categories;

  public Article(Long id, String name, String description, Double price, Integer stock, Brand brand, Set<Category> categories) {
    setId(id);
    setName(name);
    setDescription(description);
    setPrice(price);
    setStock(stock);
    setBrand(brand);
    setCategories(categories);
  }
  public Article(Long id, String name, String description, Double price, Integer stock, Brand brand, List<Category> categories) {
    setId(id);
    setName(name);
    setDescription(description);
    setPrice(price);
    setStock(stock);
    setBrand(brand);
    setCategories(categories);
  }

  public Article(){

  }
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    if(InputValidation.isNullOrEmpty(name)){
      throw new InvalidInputException(ArticleExceptionCode.EMPTY_NAME.getMessage(), ArticleExceptionCode.EMPTY_NAME.getCode());
    }
    if(InputValidation.isInvalidLength(name, 3, 50)){
      throw new InvalidInputException(ArticleExceptionCode.INVALID_NAME_LENGTH.getMessage(), ArticleExceptionCode.INVALID_NAME_LENGTH.getCode());
    }
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    if(InputValidation.isNullOrEmpty(description)){
      throw new InvalidInputException(ArticleExceptionCode.EMPTY_DESCRIPTION.getMessage(), ArticleExceptionCode.EMPTY_DESCRIPTION.getCode());
    }
    if(InputValidation.isInvalidLength(description, 10, 255)){
      throw new InvalidInputException(ArticleExceptionCode.INVALID_DESCRIPTION_LENGTH.getMessage(), ArticleExceptionCode.INVALID_DESCRIPTION_LENGTH.getCode());
    }
    this.description = description;
  }

  public Brand getBrand() {
    return brand;
  }

  public void setBrand(Brand brand) {
    if(InputValidation.isNull(brand)){
      throw new InvalidInputException(ArticleExceptionCode.EMPTY_BRAND.getMessage(), ArticleExceptionCode.EMPTY_BRAND.getCode());
    }
    this.brand = brand;
  }

  public Set<Category> getCategories() {
    return categories;
  }

  public void setCategories(Set<Category> categories) {
    if(InputValidation.isNull(categories) || categories.isEmpty()){
      throw new InvalidInputException(ArticleExceptionCode.EMPTY_CATEGORIES.getMessage(), ArticleExceptionCode.EMPTY_CATEGORIES.getCode());
    }
    if(categories.size() > 3){
      throw new InvalidInputException(ArticleExceptionCode.LIMIT_CATEGORIES_EXCEEDED.getMessage(), ArticleExceptionCode.LIMIT_CATEGORIES_EXCEEDED.getCode());
    }
    this.categories = categories;
  }

  public void setCategories(List<Category> categories) {
    if(InputValidation.isNull(categories) || categories.isEmpty()){
      throw new InvalidInputException(ArticleExceptionCode.EMPTY_CATEGORIES.getMessage(), ArticleExceptionCode.EMPTY_CATEGORIES.getCode());
    }
    if(categories.size() > 3){
      throw new InvalidInputException(ArticleExceptionCode.LIMIT_CATEGORIES_EXCEEDED.getMessage(), ArticleExceptionCode.LIMIT_CATEGORIES_EXCEEDED.getCode());
    }
    Set<Category> categoriesSet = Set.copyOf(categories);
    if(categoriesSet.size() != categories.size()){
      throw new InvalidInputException(ArticleValidationMessage.ARTICLE_CATEGORIES_REPEATED, ArticleValidationCode.ARTICLE_CATEGORIES_REPEATED_CODE);
    }
    this.categories = categoriesSet;
  }

  public Double getPrice() {
    return price;
  }
  public void setPrice(Double price) {
    if(InputValidation.isNull(price)){
      throw new InvalidInputException(ArticleExceptionCode.EMPTY_PRICE.getMessage(), ArticleExceptionCode.EMPTY_PRICE.getCode());
    }
    if(price <= 0){
      throw new InvalidInputException(ArticleExceptionCode.INVALID_PRICE.getMessage(), ArticleExceptionCode.INVALID_PRICE.getCode());
    }
    this.price = price;
  }

  public Integer getStock() {
    return stock;
  }

  public void setStock(Integer stock) {
    if(InputValidation.isNull(stock)){
      throw new InvalidInputException(ArticleExceptionCode.EMPTY_STOCK.getMessage(), ArticleExceptionCode.EMPTY_STOCK.getCode());
    }
    if(stock < 0){
      throw new InvalidInputException(ArticleExceptionCode.INVALID_STOCK.getMessage(), ArticleExceptionCode.INVALID_STOCK.getCode());
    }
    this.stock = stock;
  }
}
