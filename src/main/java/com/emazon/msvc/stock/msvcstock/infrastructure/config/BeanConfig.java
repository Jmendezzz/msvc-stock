package com.emazon.msvc.stock.msvcstock.infrastructure.config;

import com.emazon.msvc.stock.msvcstock.domain.ports.in.usecases.CreateBrandUseCase;
import com.emazon.msvc.stock.msvcstock.domain.ports.in.usecases.CreateCategoryUseCase;
import com.emazon.msvc.stock.msvcstock.domain.ports.in.usecases.RetrieveBrandUseCase;
import com.emazon.msvc.stock.msvcstock.domain.ports.in.usecases.RetrieveCategoryUseCase;
import com.emazon.msvc.stock.msvcstock.domain.ports.out.repositories.BrandRepository;
import com.emazon.msvc.stock.msvcstock.domain.ports.out.repositories.CategoryRepository;
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

}
