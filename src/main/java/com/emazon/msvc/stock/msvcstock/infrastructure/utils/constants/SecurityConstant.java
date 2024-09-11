package com.emazon.msvc.stock.msvcstock.infrastructure.utils.constants;

public class SecurityConstant {
  public static final String TOKEN_PREFIX = "Bearer ";
  public static final String ADMIN_ROLE = "ADMIN";
  public static final String ROLE_PREFIX = "ROLE_";
  public static final String ACCESS_DENIED_MESSAGE = "You do not have permission to access this resource";
  public static final String INVALID_TOKEN_MESSAGE = "Invalid token";
  private SecurityConstant() {
  }
}
