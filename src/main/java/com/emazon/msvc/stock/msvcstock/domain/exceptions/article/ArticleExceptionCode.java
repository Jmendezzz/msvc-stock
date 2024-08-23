package com.emazon.msvc.stock.msvcstock.domain.exceptions.article;

public enum ArticleExceptionCode {
  EMPTY_NAME("AI001","Article name is required"),
  INVALID_NAME_LENGTH("AI002","Article name must be between 3 and 50 characters"),
  EMPTY_DESCRIPTION("AI003","Article description is required"),
  INVALID_DESCRIPTION_LENGTH("AI004","Article description must be between 10 and 255 characters"),
  EMPTY_BRAND("AI005","Article brand is required"),
  EMPTY_CATEGORIES("AI006","Article categories are required"),
  LIMIT_CATEGORIES_EXCEEDED("AI007","Article can have up to 3 categories"),
  INVALID_CATEGORIES("A008","One or more categories were not found."),;


  private final String code;
  private final String message;

  private ArticleExceptionCode(String code, String message){
    this.code = code;
    this.message = message;
  }

  public String getCode(){
    return code;
  }

  public String getMessage(){
    return message;
  }

}
