package com.emazon.msvc.stock.msvcstock.domain.factories;

import com.emazon.msvc.stock.msvcstock.domain.validations.SortingValidation;
import com.emazon.msvc.stock.msvcstock.domain.validations.imp.SortingValidationImp;

import static com.emazon.msvc.stock.msvcstock.domain.utils.constants.sorting.SortingConstant.*;

public class SortingValidationFactory {
  private SortingValidationFactory() {
  }
  public static SortingValidation getSortingValidation(Class<?> domainClass) {

    return switch (domainClass.getSimpleName()) {
      case "Category" -> new SortingValidationImp(CATEGORY_ALLOWED_SORTING_FIELDS, CATEGORY_DEFAULT_SORT);
      case "Brand" -> new SortingValidationImp(BRAND_ALLOWED_SORTING_FIELDS, BRAND_DEFAULT_SORT);
      case "Article" -> new SortingValidationImp(ARTICLE_ALLOWED_SORTING_FIELDS, ARTICLE_DEFAULT_SORT);
      default -> throw new IllegalArgumentException("Invalid domain class");
    };
  }
}
