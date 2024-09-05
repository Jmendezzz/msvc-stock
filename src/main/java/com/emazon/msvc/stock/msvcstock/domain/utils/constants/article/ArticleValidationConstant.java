package com.emazon.msvc.stock.msvcstock.domain.utils.constants.article;

public class ArticleValidationConstant {
  public static final int ARTICLE_NAME_MIN_LENGTH = 3;
  public static final int ARTICLE_NAME_MAX_LENGTH = 50;
  public static final int ARTICLE_DESCRIPTION_MIN_LENGTH = 10;
  public static final int ARTICLE_DESCRIPTION_MAX_LENGTH = 255;
  public static final double ARTICLE_MIN_PRICE = 0.0;
  public static final String ARTICLE_MIN_PRICE_STRING = "0.0";
  public static final int ARTICLE_MIN_STOCK = 0;
  public static final int ARTICLE_MIN_CATEGORIES = 1;
  public static final int ARTICLE_MAX_CATEGORIES = 3;
  private ArticleValidationConstant() {}
}
