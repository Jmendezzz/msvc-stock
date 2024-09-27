package com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.repositories;

import com.emazon.msvc.stock.msvcstock.domain.models.*;
import com.emazon.msvc.stock.msvcstock.domain.ports.out.repositories.ArticleRepository;
import com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.entities.ArticleEntity;
import com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.mappers.DboArticleMapper;
import com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.repositories.jpa.JpaArticleRepository;
import com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.repositories.specifications.article.ArticleSpecificationBuilder;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.repositories.utils.constants.ArticleRepositoryConstant.ARTICLE_SORTING_FIELDS;
import static com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.repositories.utils.constants.ArticleRepositoryConstant.ARTICLE_SORT_BY_DEFAULT_FIELD;

@Repository
@AllArgsConstructor
public class ArticleRepositoryImp implements ArticleRepository {
  private final JpaArticleRepository jpaArticleRepository;
  private final DboArticleMapper mapper;
  @Override
  public Article save(Article article) {
    ArticleEntity articleEntity = jpaArticleRepository.save(mapper.toEntity(article));
    return mapper.toDomain(articleEntity);
  }

  @Override
  public Paginated<Article> retrieveArticles(Pagination pagination,Sorting sorting, ArticleSearchCriteria articleSearchCriteria) {

    Pageable pageable = PageRequest.of(
            pagination.getPage(),
            pagination.getSize(),
            Sort.by(Sort.Direction.fromString(
                    sorting.getDirection().name()),
                    ARTICLE_SORTING_FIELDS.getOrDefault(sorting.getSortBy(), ARTICLE_SORT_BY_DEFAULT_FIELD)
            )
    );
    Specification<ArticleEntity > specifications = new ArticleSpecificationBuilder()
            .withArticleIds(articleSearchCriteria.getArticleIds())
            .withBrand(articleSearchCriteria.getBrandId())
            .withCategory(articleSearchCriteria.getCategoryId())
            .build();

    return mapper.toDomainPaginated(jpaArticleRepository.findAll(specifications, pageable));
  }

  @Override
  public Optional<Article> findById(Long articleId) {
    return jpaArticleRepository.findById(articleId).map(mapper::toDomain);
  }

  @Override
  public boolean existsById(Long articleId) {
    return jpaArticleRepository.existsById(articleId);
  }

  @Override
  public List<Article> findAllByIds(List<Long> articleIds) {
    return mapper.toDomain(
            jpaArticleRepository.findAllById(articleIds)
    );
  }

}
