package com.emazon.msvc.stock.msvcstock.domain.models;

import com.emazon.msvc.stock.msvcstock.domain.exceptions.InvalidInputsException;
import com.emazon.msvc.stock.msvcstock.domain.exceptions.brand.BrandExceptionCode;
import com.emazon.msvc.stock.msvcstock.domain.utils.InputValidation;

import java.util.HashMap;
import java.util.Map;

public class Brand {
  private Long id;
  private String name;
  private String description;

  public Brand(Long id, String name, String description) {
    validate(name, description);
    this.id = id;
    this.name = name;
    this.description = description;
  }

  private void validate(String name, String description){
    Map<String, String> errors = new HashMap<>();

    if (InputValidation.isNullOrEmpty(name)) {
      errors.put("name", BrandExceptionCode.EMPTY_NAME.getMessage());
    } else if (InputValidation.isInvalidLength(name, 3, 50)) {
      errors.put("name", BrandExceptionCode.INVALID_NAME_LENGTH.getMessage());
    }

    if (InputValidation.isNullOrEmpty(description)) {
      errors.put("description", BrandExceptionCode.EMPTY_DESCRIPTION.getMessage());
    } else if (InputValidation.isInvalidLength(description, 3, 120)) {
      errors.put("description", BrandExceptionCode.INVALID_DESCRIPTION_LENGTH.getMessage());
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
}

