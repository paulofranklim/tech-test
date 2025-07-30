package com.paulofranklim.inditex.test.domain.usecase;

import com.paulofranklim.inditex.test.domain.model.Price;

import java.time.LocalDateTime;

public interface GetPriceUseCase {
    Price execute(Long brandId, Long productId, LocalDateTime applicationDate);
}