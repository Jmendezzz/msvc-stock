package com.emazon.msvc.stock.msvcstock.domain.ports.in.usecases;

import com.emazon.msvc.stock.msvcstock.domain.models.Category;
import com.emazon.msvc.stock.msvcstock.domain.models.Paginated;
import com.emazon.msvc.stock.msvcstock.domain.models.Pagination;
import com.emazon.msvc.stock.msvcstock.domain.models.Sorting;

import java.util.List;

public interface RetrieveCategoryUseCase {
  Paginated<Category> retrieveCategories(Pagination pagination, Sorting sort);
}
