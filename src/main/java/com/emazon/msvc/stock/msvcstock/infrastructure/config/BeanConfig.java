package com.emazon.msvc.stock.msvcstock.infrastructure.config;

import com.emazon.msvc.stock.msvcstock.domain.ports.in.usecases.article.CreateArticleUseCase;
import com.emazon.msvc.stock.msvcstock.domain.ports.in.usecases.article.RetrieveArticleUseCase;
import com.emazon.msvc.stock.msvcstock.domain.ports.in.usecases.brand.CreateBrandUseCase;
import com.emazon.msvc.stock.msvcstock.domain.ports.in.usecases.category.CreateCategoryUseCase;
import com.emazon.msvc.stock.msvcstock.domain.ports.in.usecases.brand.RetrieveBrandUseCase;
import com.emazon.msvc.stock.msvcstock.domain.ports.in.usecases.category.RetrieveCategoryUseCase;
import com.emazon.msvc.stock.msvcstock.domain.ports.out.repositories.ArticleRepository;
import com.emazon.msvc.stock.msvcstock.domain.ports.out.repositories.BrandRepository;
import com.emazon.msvc.stock.msvcstock.domain.ports.out.repositories.CategoryRepository;
import com.emazon.msvc.stock.msvcstock.domain.usecases.article.CreateArticleUseCaseImp;
import com.emazon.msvc.stock.msvcstock.domain.usecases.article.RetrieveArticleUseCaseImp;
import com.emazon.msvc.stock.msvcstock.domain.usecases.brand.CreateBrandUseCaseImp;
import com.emazon.msvc.stock.msvcstock.domain.usecases.brand.RetrieveBrandUseCaseImp;
import com.emazon.msvc.stock.msvcstock.domain.usecases.category.CreateCategoryUseCaseImp;
import com.emazon.msvc.stock.msvcstock.domain.usecases.category.RetrieveCategoryUseCaseImp;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class BeanConfig {
  private final CategoryRepository categoryRepository;
  private final BrandRepository brandRepository;
  private final ArticleRepository articleRepository;

  @Bean
  public CreateCategoryUseCase createCategoryUseCase() {
    return new CreateCategoryUseCaseImp(categoryRepository);
  }

  @Bean
  public RetrieveCategoryUseCase retrieveCategoryUseCase() {
    return new RetrieveCategoryUseCaseImp(categoryRepository);
  }
  @Bean
  public CreateBrandUseCase createBrandUseCase() {
    return new CreateBrandUseCaseImp(brandRepository);
  }
  @Bean
  public RetrieveBrandUseCase retrieveBrandUseCase() {
    return new RetrieveBrandUseCaseImp(brandRepository);
  }

  @Bean
  public CreateArticleUseCase createArticleUseCase() {
    return new CreateArticleUseCaseImp(articleRepository, retrieveCategoryUseCase(), retrieveBrandUseCase());
  }
  @Bean
  public RetrieveArticleUseCase retrieveArticleUseCase() {
    return new RetrieveArticleUseCaseImp(articleRepository);
  }

}
