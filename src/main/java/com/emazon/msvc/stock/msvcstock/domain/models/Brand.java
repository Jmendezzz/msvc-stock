package com.emazon.msvc.stock.msvcstock.domain.models;

import com.emazon.msvc.stock.msvcstock.domain.exceptions.InvalidInputException;
import com.emazon.msvc.stock.msvcstock.domain.exceptions.brand.BrandExceptionCode;
import com.emazon.msvc.stock.msvcstock.domain.utils.InputValidation;

import java.util.HashMap;
import java.util.Map;

public class Brand {
  private Long id;
  private String name;
  private String description;

  public Brand(Long id, String name, String description) {
    setId(id);
    setName(name);
    setDescription(description);
  }

  public Brand(){

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
    if (InputValidation.isNullOrEmpty(name)) {
      throw new InvalidInputException(BrandExceptionCode.EMPTY_NAME.getMessage(), BrandExceptionCode.EMPTY_NAME.getCode());
    }
    if (InputValidation.isInvalidLength(name, 3, 50)) {
      throw new InvalidInputException(BrandExceptionCode.INVALID_NAME_LENGTH.getMessage(), BrandExceptionCode.INVALID_NAME_LENGTH.getCode());
    }
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    if (InputValidation.isNullOrEmpty(description)) {
      throw new InvalidInputException(BrandExceptionCode.EMPTY_DESCRIPTION.getMessage(), BrandExceptionCode.EMPTY_DESCRIPTION.getCode());
    }
    if (InputValidation.isInvalidLength(description, 10, 255)) {
      throw new InvalidInputException(BrandExceptionCode.INVALID_DESCRIPTION_LENGTH.getMessage(), BrandExceptionCode.INVALID_DESCRIPTION_LENGTH.getCode());
    }
    this.description = description;
  }
}

