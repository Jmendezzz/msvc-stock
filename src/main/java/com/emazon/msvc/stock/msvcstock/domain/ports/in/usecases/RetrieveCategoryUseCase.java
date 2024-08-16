package com.emazon.msvc.stock.msvcstock.domain.ports.in.usecases;

import com.emazon.msvc.stock.msvcstock.domain.models.Category;

import java.util.List;

public interface RetrieveCategoryUseCase {
  List<Category> retrieveCategories();
}
