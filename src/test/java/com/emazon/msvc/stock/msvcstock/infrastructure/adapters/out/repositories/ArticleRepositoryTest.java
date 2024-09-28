package com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.repositories;

import com.emazon.msvc.stock.msvcstock.domain.models.*;
import com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.entities.ArticleEntity;
import com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.mappers.DboArticleMapper;
import com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.repositories.jpa.JpaArticleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ArticleRepositoryTest {

  @Mock
  private JpaArticleRepository jpaArticleRepository;
  @Mock
  private DboArticleMapper mapper;
  @InjectMocks
  private ArticleRepositoryImp articleRepositoryImp;

  @Test
  void testSave() {
    // Arrange
    Article article = new Article(); // create a test Article
    ArticleEntity articleEntity = new ArticleEntity(); // create a test ArticleEntity
    when(mapper.toEntity(article)).thenReturn(articleEntity);
    when(jpaArticleRepository.save(articleEntity)).thenReturn(articleEntity);
    when(mapper.toDomain(articleEntity)).thenReturn(article);

    // Act
    Article result = articleRepositoryImp.save(article);

    // Assert
    assertEquals(article, result);
    verify(mapper).toEntity(article);
    verify(jpaArticleRepository).save(articleEntity);
    verify(mapper).toDomain(articleEntity);
  }

  @Test
  void testRetrieveArticles() {
    // Arrange
    Pagination pagination = new Pagination(1, 1);
    Sorting sorting = new Sorting("name", "ASC");

    // Create a PageRequest object matching the one used in the method
    Pageable pageable = PageRequest.of(pagination.getPage(), pagination.getSize(), Sort.by(Sort.Direction.fromString(sorting.getDirection().name()), "name"));

    Article article = new Article(); // create a test Article
    ArticleEntity articleEntity = new ArticleEntity(); // create a test ArticleEntity

    // Mock the repository and mapper
    when(jpaArticleRepository.findAll(pageable)).thenReturn(new PageImpl<>(List.of(articleEntity), pageable, 1));
    when(mapper.toDomainPaginated(any())).thenReturn(new Paginated<>(List.of(article), 1L, 1L, 1L));

    // Act
    Paginated<Article> result = articleRepositoryImp.retrieveArticles(pagination, sorting, new ArticleSearchCriteria());

    // Assert
    assertEquals(1, result.getTotalPages());
    assertEquals(1, result.getTotalItems());
    assertEquals(List.of(article), result.getData());
    verify(jpaArticleRepository).findAll(pageable);
    verify(mapper).toDomainPaginated(any());
  }


}
