package com.emazon.msvc.stock.msvcstock.domain.models;

import com.emazon.msvc.stock.msvcstock.domain.exceptions.InvalidInputsException;
import com.emazon.msvc.stock.msvcstock.domain.exceptions.article.ArticleExceptionCode;
import com.emazon.msvc.stock.msvcstock.domain.utils.InputValidation;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Article {
  private Long id;
  private String name;
  private String description;
  private Brand brand;
  private Set<Category> categories;


  public Article(Long id, String name, String description, Brand brand, Set<Category> categories) {
    validate(name, description, brand, categories);
    this.id = id;
    this.name = name;
    this.description = description;
    this.brand = brand;
    this.categories = categories;
  }

  private void validate(String name, String description, Brand brand, Set<Category> categories) {
    Map<String, String> errors = new HashMap<>();

    if (InputValidation.isNullOrEmpty(name)) {
      errors.put("name", ArticleExceptionCode.EMPTY_NAME.getMessage());
    } else if (InputValidation.isInvalidLength(name, 3, 50)) {
      errors.put("name", ArticleExceptionCode.INVALID_NAME_LENGTH.getMessage());
    }

    if (InputValidation.isNullOrEmpty(description)) {
      errors.put("description", ArticleExceptionCode.EMPTY_DESCRIPTION.getMessage());
    } else if (InputValidation.isInvalidLength(description, 10, 255)) {
      errors.put("description", ArticleExceptionCode.INVALID_DESCRIPTION_LENGTH.getMessage());
    }

    if (brand == null || InputValidation.isNullOrEmpty(brand.getName())) {
      errors.put("brand", ArticleExceptionCode.EMPTY_BRAND.getMessage());
    }

    if(categories == null || categories.isEmpty()){
      errors.put("categories", ArticleExceptionCode.EMPTY_CATEGORIES.getMessage());
    }

    if(categories.size() > 3){
      errors.put("categories", ArticleExceptionCode.LIMIT_CATEGORIES_EXCEEDED.getMessage());
    }
    if(!errors.isEmpty()){
      throw new InvalidInputsException(errors);
    }

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
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Brand getBrand() {
    return brand;
  }

  public void setBrand(Brand brand) {
    this.brand = brand;
  }

  public Set<Category> getCategories() {
    return categories;
  }

  public void setCategories(Set<Category> categories) {
    this.categories = categories;
  }
}
