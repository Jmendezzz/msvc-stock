package com.emazon.msvc.stock.msvcstock.domain.ports.out.clients;


import com.emazon.msvc.stock.msvcstock.domain.models.User;

public interface UserClient {
  User getUserDetails(String token);
}
