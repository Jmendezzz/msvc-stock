package com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.feign.imp;

import com.emazon.msvc.stock.msvcstock.domain.models.User;
import com.emazon.msvc.stock.msvcstock.domain.ports.out.clients.UserClient;
import com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.feign.UserFeignClient;
import feign.FeignException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserFeignClientAdapter implements UserClient {
  private UserFeignClient userFeignClient;
  private static final Logger logger = LoggerFactory.getLogger(UserFeignClientAdapter.class);
  @Override
  public User getUserDetails(String token) {
    try{
      return userFeignClient.getUserDetails(token);
    } catch (FeignException.BadRequest exception) {
      logger.error(exception.getMessage());
      return null;
    }
  }
}
