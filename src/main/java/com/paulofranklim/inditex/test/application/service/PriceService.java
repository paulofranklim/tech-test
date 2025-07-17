package com.paulofranklim.inditex.test.application.service;

import com.paulofranklim.inditex.test.domain.model.Price;
import com.paulofranklim.inditex.test.domain.repository.PriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PriceService {

    private final PriceRepository priceRepository;

    public Optional<Price> getApplicablePrice(Long brandId, Long productId, LocalDateTime applicationDate) {
        return priceRepository.findByBrandIdAndProductIdAndDate(brandId, productId, applicationDate)
                              .stream()
                              .max(Comparator.comparingInt(Price::getPriority));
    }
}
