package com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.repositories.utils.constants;

import java.util.Map;

public class SortingField {
  public static final Map<String, String> ARTICLE_SORTING_FIELDS = Map.of(
        "name", "name",
        "brand", "brand.name",
        "category", "categories.name");

public static final String ARTICLE_SORT_BY_DEFAULT_FIELD = "id";

  private SortingField() {
  }
}
