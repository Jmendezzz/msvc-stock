package com.emazon.msvc.stock.msvcstock.application.handlers.imp;

import com.emazon.msvc.stock.msvcstock.application.dtos.brand.BrandResponseDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.brand.CreateBrandRequestDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.pagination.PaginationDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.sorting.SortingDto;
import com.emazon.msvc.stock.msvcstock.application.mappers.BrandMapper;
import com.emazon.msvc.stock.msvcstock.application.mappers.PaginationMapper;
import com.emazon.msvc.stock.msvcstock.application.mappers.SortingMapper;
import com.emazon.msvc.stock.msvcstock.application.handlers.BrandHandler;
import com.emazon.msvc.stock.msvcstock.domain.models.Brand;
import com.emazon.msvc.stock.msvcstock.domain.models.Paginated;
import com.emazon.msvc.stock.msvcstock.domain.ports.in.usecases.brand.CreateBrandUseCase;
import com.emazon.msvc.stock.msvcstock.domain.ports.in.usecases.brand.RetrieveBrandUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class BrandHandlerImp implements BrandHandler {
  private final CreateBrandUseCase createBrandUseCase;
  private final RetrieveBrandUseCase retrieveBrandUseCase;
  private final BrandMapper mapper;
  private final PaginationMapper paginationMapper;
  private final SortingMapper sortingMapper;
  @Override
  public BrandResponseDto createBrand(CreateBrandRequestDto createBrandRequestDto) {
    Brand brandCreated = createBrandUseCase.create(mapper.toDomain(createBrandRequestDto));

    return mapper.toDto(brandCreated);
  }

  @Override
  public Paginated<BrandResponseDto> retrieveBrands(PaginationDto paginationDto, SortingDto sortingDto) {
    Paginated<Brand> brands = retrieveBrandUseCase.retrieveBrands(
            paginationMapper.toDomain(paginationDto),
            sortingMapper.toDomain(sortingDto)
    );

    return mapper.toDtoPaginated(brands);
  }

  @Override
  public List<BrandResponseDto> retrieveAllBrands() {
    return mapper.toDto(retrieveBrandUseCase.retrieveAllBrands());
  }


}
