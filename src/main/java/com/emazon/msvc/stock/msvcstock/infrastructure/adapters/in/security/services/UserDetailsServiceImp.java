package com.emazon.msvc.stock.msvcstock.infrastructure.adapters.in.security.services;

import com.emazon.msvc.stock.msvcstock.domain.models.User;
import com.emazon.msvc.stock.msvcstock.domain.ports.out.clients.UserClient;
import com.emazon.msvc.stock.msvcstock.infrastructure.adapters.in.security.models.CustomUserDetails;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsServiceImp implements UserDetailsService {
  private final UserClient userClient;

  @Override
  public UserDetails loadUserByUsername(String token) throws UsernameNotFoundException {
    User user = userClient.getUserDetails(token);
    if (user == null) {
      throw new UsernameNotFoundException("User not found with token: " + token);
    }
    return new CustomUserDetails(user);
  }
}
