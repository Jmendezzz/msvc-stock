package com.emazon.msvc.stock.msvcstock.domain.ports.in.usecases.category;

import com.emazon.msvc.stock.msvcstock.domain.models.Category;
import com.emazon.msvc.stock.msvcstock.domain.models.Paginated;
import com.emazon.msvc.stock.msvcstock.domain.models.Pagination;
import com.emazon.msvc.stock.msvcstock.domain.models.Sorting;

import java.util.List;
import java.util.Set;

public interface RetrieveCategoryUseCase {
  Paginated<Category> retrieveCategories(Pagination pagination, Sorting sort);
  Set<Category> retrieveCategoriesByIds(Set<Long> ids);
}
