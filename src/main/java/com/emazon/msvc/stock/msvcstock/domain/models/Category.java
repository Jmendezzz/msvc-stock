package com.emazon.msvc.stock.msvcstock.domain.models;

import com.emazon.msvc.stock.msvcstock.domain.exceptions.InvalidInputsException;
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
    validate(name, description);
    this.id = id;
    this.name = name;
    this.description = description;
    this.createdAt = createdAt;
  }

  private void validate(String name, String description) {
    Map<String, String> errors = new HashMap<>();

    if (InputValidation.isNullOrEmpty(name)) {
      errors.put("name", CategoryExceptionCode.EMPTY_NAME.getMessage());
    } else if (InputValidation.isInvalidLength(name, 3, 50)) {
      errors.put("name", CategoryExceptionCode.INVALID_NAME_LENGTH.getMessage());
    }

    if (InputValidation.isNullOrEmpty(description)) {
      errors.put("description", CategoryExceptionCode.EMPTY_DESCRIPTION.getMessage());
    } else if (InputValidation.isInvalidLength(description, 3, 120)) {
      errors.put("description", CategoryExceptionCode.INVALID_DESCRIPTION_LENGTH.getMessage());
    }

    if (!errors.isEmpty()) {
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

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }
}
