package com.emazon.msvc.stock.msvcstock.infrastructure.adapters.in.controllers;

import com.emazon.msvc.stock.msvcstock.application.dtos.brand.BrandResponseDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.brand.CreateBrandRequestDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.pagination.PaginationDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.sorting.SortingDto;
import com.emazon.msvc.stock.msvcstock.application.handlers.BrandHandler;
import com.emazon.msvc.stock.msvcstock.domain.models.Paginated;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BrandController.class)
class BrandControllerTest {
  @Autowired
  private MockMvc mvc;
  @MockBean
  private BrandHandler brandHandler;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void testCreateBrand() throws Exception {
    // Prepare test data
    CreateBrandRequestDto requestDto = new CreateBrandRequestDto("Brand Name", "Brand Description");
    BrandResponseDto responseDto = new BrandResponseDto(1L, "Brand Name", "Brand Description");

    // Mock behavior
    when(brandHandler.createBrand(requestDto)).thenReturn(responseDto);

    // Perform the request and verify the response
    mvc.perform(post("/api/v1/brands/create")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(requestDto)))
            .andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(1L))
            .andExpect(jsonPath("$.name").value("Brand Name"))
            .andExpect(jsonPath("$.description").value("Brand Description"));
  }

  @Test
  void testCreateBrandWithInvalidData() throws Exception {
    // Prepare test data
    CreateBrandRequestDto requestDto = new CreateBrandRequestDto("", "");

    // Perform the request and verify the response
    mvc.perform(post("/api/v1/brands/create")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(requestDto)))
            .andExpect(status().isBadRequest());
  }

  @Test
  void testRetrieveBrands() throws Exception {
    // Prepare test data
    PaginationDto paginationDto = new PaginationDto(1, 10);
    SortingDto sortingDto = new SortingDto("name", "ASC");

    BrandResponseDto brand1 = new BrandResponseDto(1L, "Brand 1", "Description 1");
    BrandResponseDto brand2 = new BrandResponseDto(2L, "Brand 2", "Description 2");
    Paginated<BrandResponseDto> paginatedResponse = new Paginated<>(List.of(brand1, brand2), 1L, 2L, 1L);

    // Mock behavior
    when(brandHandler.retrieveBrands(paginationDto, sortingDto)).thenReturn(paginatedResponse);

    // Perform the request and verify the response
    mvc.perform(get("/api/v1/brands")
                    .param("page", "1")
                    .param("size", "10")
                    .param("sortBy", "name")
                    .param("direction", "ASC"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.totalItems").value(2))
            .andExpect(jsonPath("$.data[0].id").value(1L))
            .andExpect(jsonPath("$.data[0].name").value("Brand 1"))
            .andExpect(jsonPath("$.data[1].id").value(2L))
            .andExpect(jsonPath("$.data[1].name").value("Brand 2"));
  }

}
