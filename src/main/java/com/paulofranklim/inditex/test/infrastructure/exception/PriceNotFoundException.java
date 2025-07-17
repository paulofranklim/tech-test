package com.paulofranklim.inditex.test.infrastructure.exception;

import java.time.LocalDateTime;

public class PriceNotFoundException extends RuntimeException {

    public PriceNotFoundException(Long brandId, Long productId, LocalDateTime date) {
        super(String.format("Price not found for brand %d, product %d on date %s",
                brandId, productId, date));
    }
}
