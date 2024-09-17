package com.emazon.msvc.stock.msvcstock.domain.utils.constants.article;

public class ArticleValidationMessage {
  public static final String ARTICLE_NAME_REQUIRED = "Article name is required";
  public static final String ARTICLE_NAME_INVALID_LENGTH = "Article name must be between 3 and 50 characters";
  public static final String ARTICLE_DESCRIPTION_REQUIRED = "Article description is required";
  public static final String ARTICLE_DESCRIPTION_INVALID_LENGTH = "Article description must be between 10 and 255 characters";
  public static final String ARTICLE_PRICE_REQUIRED = "Article price is required";
  public static final String ARTICLE_PRICE_INVALID = "Article price must be greater than 0";
  public static final String ARTICLE_STOCK_REQUIRED = "Article stock is required";
  public static final String ARTICLE_STOCK_INVALID = "Article stock must be greater than or equal to 0";
  public static final String ARTICLE_BRAND_REQUIRED = "Article brand is required";
  public static final String ARTICLE_CATEGORIES_REQUIRED = "Article requires at least 1 category";
  public static final String ARTICLE_CATEGORIES_INVALID_LENGTH = "Article can have between 1 and 3 categories";
  public static final String ARTICLE_CATEGORIES_REPEATED = "Article categories cannot be repeated";
  public static final String ARTICLE_STOCK_UPDATE_STOCK_AMOUNT_REQUIRED = "Stock amount is required";
  public static final String ARTICLE_STOCK_UPDATE_STOCK_AMOUNT_INVALID = "Stock amount must be greater than 0";


  private ArticleValidationMessage() {}

}
