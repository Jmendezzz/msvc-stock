package com.emazon.msvc.stock.msvcstock.application.mappers;

import com.emazon.msvc.stock.msvcstock.application.dtos.pagination.PaginationDto;
import com.emazon.msvc.stock.msvcstock.domain.models.Pagination;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
public class PaginationMapperTest {

  private PaginationMapper paginationMapper;

  @BeforeEach
  public void setUp() {
    paginationMapper = Mappers.getMapper(PaginationMapper.class);
  }

  @Test
  void testMapPaginationDtoToPaginationDomain() {
    PaginationDto paginationDto = new PaginationDto(1, 10);
    Pagination expectedPagination = new Pagination(1, 10);
    Pagination pagination = paginationMapper.toDomain(paginationDto);

    assertEquals(expectedPagination.getPage(), pagination.getPage());
    assertEquals(expectedPagination.getSize(), pagination.getSize());
  }

  @Test
  void testMapNullPaginationDtoToPaginationDomain() {
    PaginationDto paginationDto = null;
    assertNull(paginationMapper.toDomain(paginationDto));
  }

  @Test
  void testDefaultPageAndSize() {
    PaginationDto paginationDto = new PaginationDto(null, null);

    assertEquals(0, paginationDto.page());
    assertEquals(10, paginationDto.size());
  }


}
