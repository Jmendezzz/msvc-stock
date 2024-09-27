package com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.repositories.jpa;

import com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.entities.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface JpaArticleRepository extends JpaRepository<ArticleEntity, Long> , JpaSpecificationExecutor<ArticleEntity> {
}
