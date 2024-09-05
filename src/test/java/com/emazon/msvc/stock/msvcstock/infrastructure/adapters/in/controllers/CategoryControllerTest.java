package com.emazon.msvc.stock.msvcstock.infrastructure.adapters.in.controllers;

import com.emazon.msvc.stock.msvcstock.application.dtos.category.CategoryResponseDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.category.CreateCategoryRequestDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.pagination.PaginationDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.sorting.SortingDto;
import com.emazon.msvc.stock.msvcstock.application.handlers.CategoryHandler;
import com.emazon.msvc.stock.msvcstock.domain.models.Paginated;
import com.fasterxml.jackson.core.JsonProcessingException;
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


@WebMvcTest(CategoryController.class)
class CategoryControllerTest {

  @Autowired
  private MockMvc mvc;
  @MockBean
  private CategoryHandler categoryHandler;
  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void testCreateCategory() throws Exception {
    // Prepare test data
    CreateCategoryRequestDto requestDto = new CreateCategoryRequestDto("Category Name", "Category Description");
    CategoryResponseDto responseDto = new CategoryResponseDto(1L, "Category Name", "Category Description");

    // Mock behavior
    when(categoryHandler.create(requestDto)).thenReturn(responseDto);

    // Perform the request and verify the response
    mvc.perform(post("/api/v1/categories/create")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(requestDto)))
            .andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(1L))
            .andExpect(jsonPath("$.name").value("Category Name"))
            .andExpect(jsonPath("$.description").value("Category Description"));
  }

  @Test
  void testCreateCategoryWithInvalidData() throws Exception {
    // Prepare test data
    CreateCategoryRequestDto requestDto = new CreateCategoryRequestDto("", "");

    // Perform the request and verify the response
    mvc.perform(post("/api/v1/categories/create")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(requestDto)))
            .andExpect(status().isBadRequest());
  }

  @Test
  void testGetCategories() throws Exception {
    PaginationDto paginationDto = new PaginationDto(1,10);
    SortingDto sortingDto = new SortingDto("name", "ASC");

    Paginated<CategoryResponseDto> paginated = new Paginated<>(List.of(new CategoryResponseDto(1L, "Category Name", "Category Description")), 1L, 1L, 1L);

    when(categoryHandler.retrieveCategories(paginationDto, sortingDto)).thenReturn(paginated);

    mvc.perform(get("/api/v1/categories")
                    .param("page", "1")
                    .param("size", "10")
                    .param("sortBy", "name")
                    .param("direction", "ASC"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.totalItems").value(1L))
            .andExpect(jsonPath("$.totalPages").value(1L))
            .andExpect(jsonPath("$.currentPage").value(1L))
            .andExpect(jsonPath("$.data[0].id").value(1L))
            .andExpect(jsonPath("$.data[0].name").value("Category Name"))
            .andExpect(jsonPath("$.data[0].description").value("Category Description"));
  }


}
