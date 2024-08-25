package com.emazon.msvc.stock.msvcstock.domain.models;

import com.emazon.msvc.stock.msvcstock.domain.exceptions.InvalidInputException;
import com.emazon.msvc.stock.msvcstock.domain.exceptions.category.CategoryExceptionCode;
import com.emazon.msvc.stock.msvcstock.domain.utils.InputValidation;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


public class Category {
  private Long id;
  private String name;
  private String description;
  private LocalDateTime createdAt;


  public Category(Long id, String name, String description, LocalDateTime createdAt) {
    setId(id);
    setName(name);
    setDescription(description);
    setCreatedAt(createdAt);
  }
  public Category(){}

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
      throw new InvalidInputException(CategoryExceptionCode.EMPTY_NAME.getMessage(),CategoryExceptionCode.EMPTY_NAME.getCode());
    }
    if(InputValidation.isInvalidLength(name,3,50)){
      throw new InvalidInputException(CategoryExceptionCode.INVALID_NAME_LENGTH.getMessage(),CategoryExceptionCode.INVALID_NAME_LENGTH.getCode());
    }
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    if(InputValidation.isNullOrEmpty(description)){
      throw new InvalidInputException(CategoryExceptionCode.EMPTY_DESCRIPTION.getMessage(),CategoryExceptionCode.EMPTY_DESCRIPTION.getCode());
    }
    if(InputValidation.isInvalidLength(description,3,120)){
      throw new InvalidInputException(CategoryExceptionCode.INVALID_DESCRIPTION_LENGTH.getMessage(),CategoryExceptionCode.INVALID_DESCRIPTION_LENGTH.getCode());
    }
    this.description = description;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }
}
