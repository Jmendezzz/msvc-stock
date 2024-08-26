package com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.repositories.jpa;

import com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.entities.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaArticleRepository extends JpaRepository<ArticleEntity, Long>{
}
