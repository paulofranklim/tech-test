package com.paulofranklim.inditex.test.infrastructure.repository.adapter;

import com.paulofranklim.inditex.test.domain.model.Price;
import com.paulofranklim.inditex.test.domain.repository.PriceRepository;
import com.paulofranklim.inditex.test.infrastructure.entity.PriceEntity;
import com.paulofranklim.inditex.test.infrastructure.repository.JpaPriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class PriceRepositoryAdapter implements PriceRepository {

    private final JpaPriceRepository jpaPriceRepository;

    @Override
    public List<Price> findByBrandIdAndProductIdAndDate(Long brandId, Long productId, LocalDateTime applicationDate) {
        List<PriceEntity> entities = jpaPriceRepository.findApplicablePrices(brandId, productId, applicationDate);
        return entities.stream().map(this::toDomain).collect(Collectors.toList());
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