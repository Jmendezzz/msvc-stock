package com.emazon.msvc.stock.msvcstock.domain.ports.in.usecases.brand;

import com.emazon.msvc.stock.msvcstock.domain.models.Brand;

public interface CreateBrandUseCase {
    Brand create(Brand brand);
}
