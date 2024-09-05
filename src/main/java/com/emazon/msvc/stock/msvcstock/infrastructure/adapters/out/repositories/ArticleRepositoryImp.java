package com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.repositories;

import com.emazon.msvc.stock.msvcstock.domain.models.Article;
import com.emazon.msvc.stock.msvcstock.domain.models.Paginated;
import com.emazon.msvc.stock.msvcstock.domain.models.Pagination;
import com.emazon.msvc.stock.msvcstock.domain.models.Sorting;
import com.emazon.msvc.stock.msvcstock.domain.ports.out.repositories.ArticleRepository;
import com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.entities.ArticleEntity;
import com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.mappers.DboArticleMapper;
import com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.repositories.jpa.JpaArticleRepository;
import com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.repositories.utils.constants.SortingField;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.repositories.utils.constants.SortingField.ARTICLE_SORTING_FIELDS;
import static com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.repositories.utils.constants.SortingField.ARTICLE_SORT_BY_DEFAULT_FIELD;

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
  public Paginated<Article> retrieveArticles(Pagination pagination,Sorting sorting) {

    Pageable pageable = PageRequest.of(
            pagination.getPage(),
            pagination.getSize(),
            Sort.by(Sort.Direction.fromString(
                    sorting.getDirection().name()),
                    ARTICLE_SORTING_FIELDS.getOrDefault(sorting.getSortBy(), ARTICLE_SORT_BY_DEFAULT_FIELD)
            )
    );

    return mapper.toDomainPaginated(jpaArticleRepository.findAll(pageable));
  }

}
