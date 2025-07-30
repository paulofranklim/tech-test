package com.paulofranklim.inditex.test.domain.repository;

import com.paulofranklim.inditex.test.domain.model.Price;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceRepository {
    Optional<Price> findByBrandIdAndProductIdAndDate(Long brandId, Long productId, LocalDateTime applicationDate);
}