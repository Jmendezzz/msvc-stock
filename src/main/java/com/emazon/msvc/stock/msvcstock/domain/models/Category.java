package com.emazon.msvc.stock.msvcstock.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category {
  private Long id;
  private String name;
  private String description;
  private LocalDateTime createdAt;
}
