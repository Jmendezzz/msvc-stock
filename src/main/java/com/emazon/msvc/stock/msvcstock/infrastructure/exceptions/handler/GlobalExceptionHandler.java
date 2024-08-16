package com.emazon.msvc.stock.msvcstock.infrastructure.exceptions.handler;

import com.emazon.msvc.stock.msvcstock.domain.exceptions.category.DuplicateCategoryNameException;
import com.emazon.msvc.stock.msvcstock.infrastructure.exceptions.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();

    ex.getBindingResult().getFieldErrors().forEach(error -> {
      String fieldName = error.getField();
      String errorMessage = error.getDefaultMessage();
      errors.put(fieldName, errorMessage);
    });

    return ResponseEntity
            .badRequest()
            .body(errors);
  }


  @ExceptionHandler(DuplicateCategoryNameException.class)
  public ResponseEntity<ExceptionResponse> handleDuplicateCategoryNameException(DuplicateCategoryNameException ex) {
    ExceptionResponse errorResponse = new ExceptionResponse(LocalDateTime.now(), ex.getMessage(),ex.getCode(),HttpStatus.BAD_REQUEST);
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

}
