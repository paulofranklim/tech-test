package com.paulofranklim.inditex.test.infrastructure.repository.adapter;

import com.paulofranklim.inditex.test.domain.model.Price;
import com.paulofranklim.inditex.test.infrastructure.repository.JpaPriceRepository;
import com.paulofranklim.inditex.test.infrastructure.repository.PriceEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceRepositoryAdapterTest {

    @Mock
    private JpaPriceRepository jpaPriceRepository;

    @InjectMocks
    private PriceRepositoryAdapter priceRepositoryAdapter;

    @Test
    void findByBrandIdAndProductIdAndDate_shouldReturnPriceWhenFound() {
        Long brandId = 1L;
        Long productId = 35455L;
        LocalDateTime applicationDate = LocalDateTime.now();

        PriceEntity priceEntity = PriceEntity.builder()
                .id(1L)
                .brandId(brandId)
                .productId(productId)
                .startDate(applicationDate.minusDays(1))
                .endDate(applicationDate.plusDays(1))
                .priceList(1)
                .priority(0)
                .price(new BigDecimal("30.00"))
                .currency("EUR")
                .build();

        when(jpaPriceRepository.findApplicablePrices(eq(brandId), eq(productId), eq(applicationDate), any(PageRequest.class)))
                .thenReturn(List.of(priceEntity));

        Optional<Price> result = priceRepositoryAdapter.findByBrandIdAndProductIdAndDate(brandId, productId, applicationDate);

        assertTrue(result.isPresent());
        assertEquals(priceEntity.getProductId(), result.get().getProductId());
        assertEquals(priceEntity.getPrice(), result.get().getPrice());
    }

    @Test
    void findByBrandIdAndProductIdAndDate_shouldReturnEmptyWhenNotFound() {
        Long brandId = 1L;
        Long productId = 35455L;
        LocalDateTime applicationDate = LocalDateTime.now();

        when(jpaPriceRepository.findApplicablePrices(eq(brandId), eq(productId), eq(applicationDate), any(PageRequest.class)))
                .thenReturn(Collections.emptyList());

        Optional<Price> result = priceRepositoryAdapter.findByBrandIdAndProductIdAndDate(brandId, productId, applicationDate);

        assertTrue(result.isEmpty());
    }
}