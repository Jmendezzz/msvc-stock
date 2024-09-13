package com.emazon.msvc.stock.msvcstock.infrastructure.adapters.in.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.emazon.msvc.stock.msvcstock.infrastructure.utils.constants.SecurityConstant.ADMIN_ROLE;


@Configuration
public class RolesConfig {
  @Bean
  public String adminRole() {
    return ADMIN_ROLE;
  }
}
