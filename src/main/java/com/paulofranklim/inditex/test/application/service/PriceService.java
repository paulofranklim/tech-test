package com.paulofranklim.inditex.test.application.service;

import com.paulofranklim.inditex.test.domain.exception.PriceNotFoundException;
import com.paulofranklim.inditex.test.domain.model.Price;
import com.paulofranklim.inditex.test.domain.repository.PriceRepository;
import com.paulofranklim.inditex.test.domain.usecase.GetPriceUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PriceService implements GetPriceUseCase {

    private final PriceRepository priceRepository;

    @Override
    public Price execute(Long brandId, Long productId, LocalDateTime applicationDate) {
        return priceRepository.findByBrandIdAndProductIdAndDate(brandId, productId, applicationDate)
                .orElseThrow(() -> new PriceNotFoundException(brandId, productId, applicationDate));
    }
}