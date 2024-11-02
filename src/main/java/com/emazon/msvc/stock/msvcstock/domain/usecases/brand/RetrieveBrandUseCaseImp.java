package com.emazon.msvc.stock.msvcstock.domain.usecases.brand;

import com.emazon.msvc.stock.msvcstock.domain.factories.SortingValidationFactory;
import com.emazon.msvc.stock.msvcstock.domain.models.Brand;
import com.emazon.msvc.stock.msvcstock.domain.models.Paginated;
import com.emazon.msvc.stock.msvcstock.domain.models.Pagination;
import com.emazon.msvc.stock.msvcstock.domain.models.Sorting;
import com.emazon.msvc.stock.msvcstock.domain.ports.in.usecases.brand.RetrieveBrandUseCase;
import com.emazon.msvc.stock.msvcstock.domain.ports.out.repositories.BrandRepository;
import com.emazon.msvc.stock.msvcstock.domain.validations.SortingValidation;

import java.util.List;
import java.util.Optional;

public class RetrieveBrandUseCaseImp implements RetrieveBrandUseCase {
  private final BrandRepository repository;
  private final SortingValidation brandSortingValidation;

  public RetrieveBrandUseCaseImp(BrandRepository repository) {
    this.repository = repository;
    this.brandSortingValidation = SortingValidationFactory.getSortingValidation(Brand.class);
  }

  @Override
  public Paginated<Brand> retrieveBrands(Pagination pagination, Sorting sort) {
    Sorting validatedSorting = brandSortingValidation.validateSorting(sort);

    return repository.findAll(pagination, validatedSorting);
  }

  @Override
  public List<Brand> retrieveAllBrands() {
    return repository.findAll();
  }

  @Override
  public Optional<Brand> retrieveBrandById(Long id) {
    return repository.findById(id);
  }
}
