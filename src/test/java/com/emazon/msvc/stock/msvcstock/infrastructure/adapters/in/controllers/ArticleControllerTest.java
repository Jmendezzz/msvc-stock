package com.emazon.msvc.stock.msvcstock.infrastructure.adapters.in.controllers;

import com.emazon.msvc.stock.msvcstock.application.dtos.article.ArticleResponseDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.article.CreateArticleRequestDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.article.ListArticleCategoryResponseDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.article.ListArticleResponseDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.brand.BrandResponseDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.category.CategoryResponseDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.pagination.PaginationDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.sorting.SortingDto;
import com.emazon.msvc.stock.msvcstock.application.handlers.ArticleHandler;
import com.emazon.msvc.stock.msvcstock.domain.models.Paginated;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ArticleController.class)
class ArticleControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private ArticleHandler articleHandler;

  @Test
  void testCreateArticle_Success() throws Exception {
    BrandResponseDto brandResponseDto = new BrandResponseDto(1L, "Test Brand", "Test Description");
    Set<CategoryResponseDto> categories = Set.of(new CategoryResponseDto(1L, "Test Category", "Category Description"));
    ArticleResponseDto responseDto = new ArticleResponseDto(1L, "Test Article", "Test Description", 100.0, 10,brandResponseDto, categories );

    Mockito.when(articleHandler.createArticle(any(CreateArticleRequestDto.class))).thenReturn(responseDto);

    mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/articles/create")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("""
                        {
                          "name": "Test Article",
                          "description": "Test Description",
                          "price": 100.0,
                          "stock": 10,
                          "brandId": 1,
                          "categoriesIds": [1]
                        }
                        """))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").value(1L))
            .andExpect(jsonPath("$.name").value("Test Article"))
            .andExpect(jsonPath("$.description").value("Test Description"))
            .andExpect(jsonPath("$.price").value(100.0))
            .andExpect(jsonPath("$.stock").value(10));
  }

  @Test
  void testCreateArticle_BadRequest_InvalidData() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/articles/create")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("""
                        {
                          "name": "",
                          "description": "",
                          "price": -100.0,
                          "stock": -10,
                          "brandId": 1,
                          "categoriesIds": []
                        }
                        """))
            .andExpect(status().isBadRequest());
  }

  @Test
  void testRetrieveArticles_Success() throws Exception {
    BrandResponseDto brandResponseDto = new BrandResponseDto(1L, "Test Brand", "Test Description");
    Set<ListArticleCategoryResponseDto> categories = Set.of(new ListArticleCategoryResponseDto(1L, "Test Category"));
    ListArticleResponseDto articleDto = new ListArticleResponseDto(
            1L, "Test Article", "Test Description", 100.0, 10, brandResponseDto, categories
    );

    Paginated<ListArticleResponseDto> paginated = new Paginated<>(Collections.singletonList(articleDto), 1L, 1L, 1L);

    Mockito.when(articleHandler.retrieveArticles(any(PaginationDto.class), any(SortingDto.class)))
            .thenReturn(paginated);

    mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/articles")
                    .param("page", "1")
                    .param("size", "10")
                    .param("sort", "name,asc")
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data[0].id").value(1L))
            .andExpect(jsonPath("$.data[0].name").value("Test Article"))
            .andExpect(jsonPath("$.totalPages").value(1))
            .andExpect(jsonPath("$.totalItems").value(1))
            .andExpect(jsonPath("$.currentPage").value(1))
            .andExpect(jsonPath("$.data[0].brand.id").value(1L))
            .andExpect(jsonPath("$.data[0].brand.name").value("Test Brand"))
            .andExpect(jsonPath("$.data[0].brand.description").value("Test Description"))
            .andExpect(jsonPath("$.data[0].categories[0].id").value(1L));
  }

  @Test
  void testRetrieveArticles_BadRequest_InvalidData() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/articles")
                    .param("page", "-1")
                    .param("size", "0")
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
  }
}