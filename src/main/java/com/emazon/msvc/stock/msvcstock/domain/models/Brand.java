package com.emazon.msvc.stock.msvcstock.domain.models;

import com.emazon.msvc.stock.msvcstock.domain.exceptions.InvalidInputException;
import com.emazon.msvc.stock.msvcstock.domain.utils.InputValidation;

import static com.emazon.msvc.stock.msvcstock.domain.utils.constants.brand.BrandValidationCode.*;
import static com.emazon.msvc.stock.msvcstock.domain.utils.constants.brand.BrandValidationConstant.*;
import static com.emazon.msvc.stock.msvcstock.domain.utils.constants.brand.BrandValidationMessage.*;

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
      throw new InvalidInputException(BRAND_NAME_REQUIRED, BRAND_NAME_REQUIRED_CODE);
    }
    if (InputValidation.isInvalidLength(name, BRAND_NAME_MIN_LENGTH, BRAND_NAME_MAX_LENGTH)) {
      throw new InvalidInputException(BRAND_NAME_INVALID_LENGTH, BRAND_NAME_INVALID_LENGTH_CODE);
    }
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    if (InputValidation.isNullOrEmpty(description)) {
      throw new InvalidInputException(BRAND_DESCRIPTION_REQUIRED, BRAND_DESCRIPTION_REQUIRED_CODE);
    }
    if (InputValidation.isInvalidLength(description, BRAND_DESCRIPTION_MIN_LENGTH, BRAND_DESCRIPTION_MAX_LENGTH)) {
      throw new InvalidInputException(BRAND_DESCRIPTION_INVALID_LENGTH, BRAND_DESCRIPTION_INVALID_LENGTH_CODE);
    }
    this.description = description;
  }
}

