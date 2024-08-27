package com.emazon.msvc.stock.msvcstock.domain.factories;

import com.emazon.msvc.stock.msvcstock.domain.validations.SortingValidation;
import com.emazon.msvc.stock.msvcstock.domain.validations.imp.ArticleSortingValidation;
import com.emazon.msvc.stock.msvcstock.domain.validations.imp.BrandSortingValidation;
import com.emazon.msvc.stock.msvcstock.domain.validations.imp.CategorySortingValidation;

public class SortingValidationFactory {
  private SortingValidationFactory() {
  }
  public static SortingValidation getSortingValidation(Class<?> domainClass) {

    return switch (domainClass.getSimpleName()) {
      case "Category" -> new CategorySortingValidation();
      case "Brand" -> new BrandSortingValidation();
      case "Article" -> new ArticleSortingValidation();
      default -> throw new IllegalArgumentException("Invalid domain class");
    };
  }
}
