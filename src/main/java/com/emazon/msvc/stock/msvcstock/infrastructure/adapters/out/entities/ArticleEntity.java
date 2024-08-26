package com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "articles")
public class ArticleEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String description;
  private Double price;
  private Integer stock;
  @ManyToOne
  private BrandEntity brand;
  @ManyToMany
  @JoinTable(
    name = "articles_categories",
    joinColumns = @JoinColumn(name = "article_id"),
    inverseJoinColumns = @JoinColumn(name = "category_id")
  )
  private Set<CategoryEntity> categories;
}
