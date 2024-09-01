package com.emazon.msvc.stock.msvcstock.application.mappers;

import com.emazon.msvc.stock.msvcstock.application.dtos.sorting.SortingDto;
import com.emazon.msvc.stock.msvcstock.domain.models.Sorting;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class SortingMapperTest {
  private SortingMapper sortingMapper;

  @BeforeEach
  void setUp(){
    sortingMapper = Mappers.getMapper(SortingMapper.class);
  }

  @Test
  void testMapSortingDtoToSortingDomain(){
    SortingDto sortingDto = new SortingDto("name", "ASC");
    Sorting expectedSorting = new Sorting("name", "ASC");
    Sorting sorting = sortingMapper.toDomain(sortingDto);

    assertEquals(expectedSorting.getSortBy(), sorting.getSortBy());
    assertEquals(expectedSorting.getDirection(), sorting.getDirection());
  }

  @Test
  void testMapNullSortingDtoToSortingDomain(){
    SortingDto sortingDto = null;
    assertNull(sortingMapper.toDomain(sortingDto));
  }

}
