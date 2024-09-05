package com.emazon.msvc.stock.msvcstock.domain.models;

import com.emazon.msvc.stock.msvcstock.domain.exceptions.InvalidInputException;
import com.emazon.msvc.stock.msvcstock.domain.utils.InputValidation;

import static com.emazon.msvc.stock.msvcstock.domain.utils.constants.category.CategoryValidationConstant.*;
import static com.emazon.msvc.stock.msvcstock.domain.utils.constants.category.CategoryValidationMessage.*;
import static com.emazon.msvc.stock.msvcstock.domain.utils.constants.category.CategoryValidationCode.*;


public class Category {
  private Long id;
  private String name;
  private String description;

  public Category(Long id, String name, String description) {
    setId(id);
    setName(name);
    setDescription(description);
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
      throw new InvalidInputException(CATEGORY_NAME_REQUIRED,CATEGORY_NAME_REQUIRED_CODE);
    }
    if(InputValidation.isInvalidLength(name,CATEGORY_NAME_MIN_LENGTH,CATEGORY_NAME_MAX_LENGTH)){
      throw new InvalidInputException(CATEGORY_NAME_LENGTH,CATEGORY_NAME_LENGTH_CODE);
    }
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    if(InputValidation.isNullOrEmpty(description)){
      throw new InvalidInputException(CATEGORY_DESCRIPTION_REQUIRED,CATEGORY_DESCRIPTION_REQUIRED_CODE);
    }
    if(InputValidation.isInvalidLength(description,CATEGORY_DESCRIPTION_MIN_LENGTH,CATEGORY_DESCRIPTION_MAX_LENGTH)){
      throw new InvalidInputException(CATEGORY_DESCRIPTION_LENGTH,CATEGORY_DESCRIPTION_LENGTH_CODE);
    }
    this.description = description;
  }

}
