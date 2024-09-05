package com.emazon.msvc.stock.msvcstock.domain.models;

import com.emazon.msvc.stock.msvcstock.domain.exceptions.InvalidInputException;
import com.emazon.msvc.stock.msvcstock.domain.utils.InputValidation;

import static com.emazon.msvc.stock.msvcstock.domain.utils.constants.article.ArticleValidationCode.*;
import static com.emazon.msvc.stock.msvcstock.domain.utils.constants.article.ArticleValidationMessage.*;
import static com.emazon.msvc.stock.msvcstock.domain.utils.constants.article.ArticleValidationConstant.*;

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
      throw new InvalidInputException(ARTICLE_NAME_REQUIRED, ARTICLE_NAME_REQUIRED_CODE);
    }
    if(InputValidation.isInvalidLength(name, ARTICLE_NAME_MIN_LENGTH, ARTICLE_NAME_MAX_LENGTH)){
      throw new InvalidInputException(ARTICLE_NAME_INVALID_LENGTH, ARTICLE_NAME_INVALID_LENGTH_CODE);
    }
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    if(InputValidation.isNullOrEmpty(description)){
      throw new InvalidInputException(ARTICLE_DESCRIPTION_REQUIRED, ARTICLE_DESCRIPTION_REQUIRED_CODE);
    }
    if(InputValidation.isInvalidLength(description, ARTICLE_DESCRIPTION_MIN_LENGTH, ARTICLE_DESCRIPTION_MAX_LENGTH)){
      throw new InvalidInputException(ARTICLE_DESCRIPTION_INVALID_LENGTH, ARTICLE_DESCRIPTION_INVALID_LENGTH_CODE);
    }
    this.description = description;
  }

  public Brand getBrand() {
    return brand;
  }

  public void setBrand(Brand brand) {
    if(InputValidation.isNull(brand)){
      throw new InvalidInputException(ARTICLE_BRAND_REQUIRED, ARTICLE_BRAND_REQUIRED_CODE);
    }
    this.brand = brand;
  }

  public Set<Category> getCategories() {
    return categories;
  }

  public void setCategories(Set<Category> categories) {
    if(InputValidation.isNull(categories) || categories.isEmpty()){
      throw new InvalidInputException(ARTICLE_CATEGORIES_REQUIRED, ARTICLE_CATEGORIES_REQUIRED_CODE);
    }
    if(categories.size() > ARTICLE_MAX_CATEGORIES){
      throw new InvalidInputException(ARTICLE_CATEGORIES_INVALID_LENGTH, ARTICLE_CATEGORIES_INVALID_LENGTH);
    }
    this.categories = categories;
  }

  public void setCategories(List<Category> categories) {
    if(InputValidation.isNull(categories) || categories.isEmpty()){
      throw new InvalidInputException(ARTICLE_CATEGORIES_REQUIRED, ARTICLE_CATEGORIES_REQUIRED_CODE);
    }
    if(categories.size() > ARTICLE_MAX_CATEGORIES){
      throw new InvalidInputException(ARTICLE_CATEGORIES_INVALID_LENGTH, ARTICLE_CATEGORIES_INVALID_LENGTH);
    }
    Set<Category> categoriesSet = Set.copyOf(categories);
    if(categoriesSet.size() != categories.size()){
      throw new InvalidInputException(ARTICLE_CATEGORIES_REPEATED, ARTICLE_CATEGORIES_REPEATED_CODE);
    }
    this.categories = categoriesSet;
  }

  public Double getPrice() {
    return price;
  }
  public void setPrice(Double price) {
    if(InputValidation.isNull(price)){
      throw new InvalidInputException(ARTICLE_PRICE_REQUIRED, ARTICLE_PRICE_REQUIRED_CODE);
    }
    if(price <= ARTICLE_MIN_PRICE){
      throw new InvalidInputException(ARTICLE_PRICE_INVALID, ARTICLE_PRICE_INVALID_CODE);
    }
    this.price = price;
  }

  public Integer getStock() {
    return stock;
  }

  public void setStock(Integer stock) {
    if(InputValidation.isNull(stock)){
      throw new InvalidInputException(ARTICLE_STOCK_REQUIRED, ARTICLE_STOCK_REQUIRED_CODE);
    }
    if(stock < ARTICLE_MIN_STOCK){
      throw new InvalidInputException(ARTICLE_STOCK_INVALID, ARTICLE_STOCK_INVALID_CODE);
    }
    this.stock = stock;
  }
}
