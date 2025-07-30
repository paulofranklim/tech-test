package com.paulofranklim.inditex.test.infrastructure.repository.adapter;

import com.paulofranklim.inditex.test.domain.model.Price;
import com.paulofranklim.inditex.test.domain.repository.PriceRepository;
import com.paulofranklim.inditex.test.infrastructure.repository.JpaPriceRepository;
import com.paulofranklim.inditex.test.infrastructure.repository.PriceEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PriceRepositoryAdapter implements PriceRepository {

    private final JpaPriceRepository jpaPriceRepository;

    @Override
    public Optional<Price> findByBrandIdAndProductIdAndDate(Long brandId, Long productId, LocalDateTime applicationDate) {
        return jpaPriceRepository.findApplicablePrices(brandId, productId, applicationDate, PageRequest.of(0, 1))
                .stream()
                .findFirst()
                .map(this::toDomain);
    }

    private Price toDomain(PriceEntity entity) {
        return new Price(
                entity.getBrandId(),
                entity.getStartDate(),
                entity.getEndDate(),
                entity.getPriceList(),
                entity.getProductId(),
                entity.getPriority(),
                entity.getPrice(),
                entity.getCurrency()
        );
    }
}