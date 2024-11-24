package com.emazon.msvc.stock.msvcstock.infrastructure.adapters.in.security.filters;

import com.emazon.msvc.stock.msvcstock.domain.models.User;
import com.emazon.msvc.stock.msvcstock.infrastructure.adapters.in.security.models.CustomUserDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static com.emazon.msvc.stock.msvcstock.infrastructure.utils.constants.SecurityConstant.MACHINE_ROLE;
import static com.emazon.msvc.stock.msvcstock.infrastructure.utils.constants.SecurityConstant.MACHINE_USERNAME;

@Component
public class MachineAuthenticationFilter extends OncePerRequestFilter {

  @Value("${security.machine.key}")
  private String machineKey;
  @Value("${security.machine.header}")
  private String machineHeader;
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    String machineKeyRequest = request.getHeader(machineHeader);
    if(isValidMachineKey(machineKeyRequest)){
      UserDetails machineAuthentication = createMachineUserDetails();
      Authentication authentication = new UsernamePasswordAuthenticationToken(machineAuthentication, null, machineAuthentication.getAuthorities());

      SecurityContext context = SecurityContextHolder.getContext();
      context.setAuthentication(authentication);
    }
    filterChain.doFilter(request, response);
  }

  private Boolean isValidMachineKey(String key){
    if(key == null) return false;
    return key.equals(machineKey);
  }

  private UserDetails createMachineUserDetails(){
    return new CustomUserDetails(
            new User(null, MACHINE_USERNAME,MACHINE_ROLE)
    );
  }
}
