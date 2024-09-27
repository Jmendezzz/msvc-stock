package com.emazon.msvc.stock.msvcstock.infrastructure.exceptions.handler;

import com.emazon.msvc.stock.msvcstock.domain.exceptions.BusinessException;
import com.emazon.msvc.stock.msvcstock.infrastructure.exceptions.EntityNotFoundException;
import com.emazon.msvc.stock.msvcstock.infrastructure.exceptions.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
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


  @ExceptionHandler(BusinessException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<ExceptionResponse> handleBusinessException(BusinessException ex) {
    ExceptionResponse errorResponse = new ExceptionResponse(LocalDateTime.now(),ex.getCode(),ex.getMessage(),HttpStatus.BAD_REQUEST);
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(EntityNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseEntity<ExceptionResponse> handleEntityNotFoundException(EntityNotFoundException ex) {
    ExceptionResponse errorResponse = new ExceptionResponse(LocalDateTime.now(),HttpStatus.NOT_FOUND.name(),ex.getMessage(),HttpStatus.NOT_FOUND);
    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
  }

}
