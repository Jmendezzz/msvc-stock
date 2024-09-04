package com.emazon.msvc.stock.msvcstock.domain.utils.constants.sorting;

import com.emazon.msvc.stock.msvcstock.domain.enums.SortDirection;

import java.util.Set;

public class SortingConstant {
  public static final Set<String> CATEGORY_ALLOWED_SORTING_FIELDS = Set.of("name");
  public static final String CATEGORY_DEFAULT_SORT = "name";
  public static final Set<String> ARTICLE_ALLOWED_SORTING_FIELDS = Set.of("name", "brand", "category");
  public static final String ARTICLE_DEFAULT_SORT = "name";
  public static final Set<String> BRAND_ALLOWED_SORTING_FIELDS = Set.of("name");
  public static final String BRAND_DEFAULT_SORT = "name";
  public static final SortDirection SORTING_DEFAULT_DIRECTION = SortDirection.ASC;
  private SortingConstant() {
  }
}
