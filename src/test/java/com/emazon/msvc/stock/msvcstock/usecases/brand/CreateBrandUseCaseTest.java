package com.emazon.msvc.stock.msvcstock.usecases.brand;

import com.emazon.msvc.stock.msvcstock.domain.exceptions.brand.DuplicateBrandNameException;
import com.emazon.msvc.stock.msvcstock.domain.models.Brand;
import com.emazon.msvc.stock.msvcstock.domain.ports.in.usecases.CreateBrandUseCase;
import com.emazon.msvc.stock.msvcstock.domain.ports.out.repositories.BrandRepository;
import com.emazon.msvc.stock.msvcstock.domain.usecases.brand.CreateBrandUseCaseImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class CreateBrandUseCaseTest {

  private  CreateBrandUseCase createBrandUseCase;
  @Mock
  private  BrandRepository brandRepository;
  @BeforeEach
  public void setUp() {
    createBrandUseCase = new CreateBrandUseCaseImp(brandRepository);
  }

  @Test
  void createBrandTest() {
    Brand expectedBrand = new Brand(1L, "brandName", "brandDescription");
    when(brandRepository.findByName(expectedBrand.getName())).thenReturn(Optional.empty());

    when(brandRepository.save(expectedBrand)).thenReturn(expectedBrand);

    Brand createdBrand = createBrandUseCase.create(expectedBrand);

    assertEquals(expectedBrand, createdBrand);
  }

  @Test
  void createBrandWithDuplicateNameTest() {
    Brand expectedBrand = new Brand(1L, "brandName", "brandDescription");
    when(brandRepository.findByName(expectedBrand.getName())).thenReturn(Optional.of(expectedBrand));

    assertThrows(DuplicateBrandNameException.class, () -> createBrandUseCase.create(expectedBrand));
  }


}
