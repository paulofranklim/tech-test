package com.paulofranklim.inditex.test.domain.usecase;

import com.paulofranklim.inditex.test.application.service.PriceService;
import com.paulofranklim.inditex.test.domain.model.Price;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class GetPriceUseCase {

    private final PriceService priceService;

    public Optional<Price> execute(Long brandId, Long productId, LocalDateTime applicationDate) {
        return priceService.getApplicablePrice(brandId, productId, applicationDate);
    }
}