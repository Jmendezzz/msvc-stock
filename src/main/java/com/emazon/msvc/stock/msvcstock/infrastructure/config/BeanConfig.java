package com.emazon.msvc.stock.msvcstock.infrastructure.config;

import com.emazon.msvc.stock.msvcstock.domain.ports.in.usecases.CreateCategoryUseCase;
import com.emazon.msvc.stock.msvcstock.domain.ports.in.usecases.RetrieveCategoryUseCase;
import com.emazon.msvc.stock.msvcstock.domain.ports.out.repositories.CategoryRepository;
import com.emazon.msvc.stock.msvcstock.domain.usecases.CreateCategoryUseCaseImp;
import com.emazon.msvc.stock.msvcstock.domain.usecases.RetrieveCategoryUseCaseImp;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class BeanConfig {
  private final CategoryRepository categoryRepository;

  @Bean
  public CreateCategoryUseCase createCategoryUseCase() {
    return new CreateCategoryUseCaseImp(categoryRepository);
  }

  @Bean
  public RetrieveCategoryUseCase retrieveCategoryUseCase() {
    return new RetrieveCategoryUseCaseImp(categoryRepository);
  }

}
