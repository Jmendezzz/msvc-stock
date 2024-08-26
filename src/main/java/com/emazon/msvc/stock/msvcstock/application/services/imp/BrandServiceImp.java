package com.emazon.msvc.stock.msvcstock.application.services.imp;

import com.emazon.msvc.stock.msvcstock.application.dtos.brand.BrandDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.brand.CreateBrandDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.pagination.PaginationDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.sorting.SortingDto;
import com.emazon.msvc.stock.msvcstock.application.mappers.BrandMapper;
import com.emazon.msvc.stock.msvcstock.application.mappers.PaginationMapper;
import com.emazon.msvc.stock.msvcstock.application.mappers.SortingMapper;
import com.emazon.msvc.stock.msvcstock.application.services.BrandService;
import com.emazon.msvc.stock.msvcstock.domain.models.Brand;
import com.emazon.msvc.stock.msvcstock.domain.models.Paginated;
import com.emazon.msvc.stock.msvcstock.domain.ports.in.usecases.brand.CreateBrandUseCase;
import com.emazon.msvc.stock.msvcstock.domain.ports.in.usecases.brand.RetrieveBrandUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class BrandServiceImp implements BrandService {
  private final CreateBrandUseCase createBrandUseCase;
  private final RetrieveBrandUseCase retrieveBrandUseCase;
  private final BrandMapper mapper;
  private final PaginationMapper paginationMapper;
  private final SortingMapper sortingMapper;
  @Override
  public BrandDto createBrand(CreateBrandDto createBrandDto) {
    Brand brandCreated = createBrandUseCase.create(mapper.toDomain(createBrandDto));

    return mapper.toDto(brandCreated);
  }

  @Override
  public Paginated<BrandDto> retrieveBrands(PaginationDto paginationDto, SortingDto sortingDto) {
    Paginated<Brand> brands = retrieveBrandUseCase.retrieveBrands(
            paginationMapper.toDomain(paginationDto),
            sortingMapper.toDomain(sortingDto)
    );

    return mapper.toDtoPaginated(brands);
  }
}
