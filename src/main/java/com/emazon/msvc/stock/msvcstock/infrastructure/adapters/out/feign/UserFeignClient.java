package com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.feign;

import com.emazon.msvc.stock.msvcstock.domain.models.User;
import com.emazon.msvc.stock.msvcstock.infrastructure.utils.constants.FeignConstant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = FeignConstant.USER_CLIENT, url = FeignConstant.USER_URL)
public interface UserFeignClient {
  @GetMapping("/validate-token")
  User getUserDetails(@RequestHeader(HttpHeaders.AUTHORIZATION) String token);
}
