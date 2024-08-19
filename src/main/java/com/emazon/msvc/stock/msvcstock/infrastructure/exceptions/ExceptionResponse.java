package com.emazon.msvc.stock.msvcstock.infrastructure.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class ExceptionResponse{
  private LocalDateTime timestamp;
  private String code;
  private String message;
  private HttpStatus status;
}
