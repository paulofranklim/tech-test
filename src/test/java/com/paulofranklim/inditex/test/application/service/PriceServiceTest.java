package com.paulofranklim.inditex.test.application.service;

import com.paulofranklim.inditex.test.domain.exception.PriceNotFoundException;
import com.paulofranklim.inditex.test.domain.model.Price;
import com.paulofranklim.inditex.test.domain.repository.PriceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceServiceTest {

    @Mock
    private PriceRepository priceRepository;

    @InjectMocks
    private PriceService priceService;

    @Test
    void shouldReturnPriceWithHighestPriority() {
        Long brandId = 1L;
        Long productId = 35455L;
        LocalDateTime applicationDate = LocalDateTime.now();

        Price price1 = new Price(brandId, applicationDate, applicationDate.plusDays(1), 1, productId, 0, new BigDecimal("35.50"), "EUR");
        Price price2 = new Price(brandId, applicationDate, applicationDate.plusDays(1), 2, productId, 1, new BigDecimal("25.45"), "EUR");

        when(priceRepository.findByBrandIdAndProductIdAndDate(brandId, productId, applicationDate)).thenReturn(Optional.of(price2));

        Price result = priceService.execute(brandId, productId, applicationDate);

        assertNotNull(result);
        assertEquals(0, new BigDecimal("25.45").compareTo(result.getPrice()));
    }

    @Test
    void shouldThrowExceptionWhenNoPriceFound() {
        Long brandId = 1L;
        Long productId = 35455L;
        LocalDateTime applicationDate = LocalDateTime.now();

        when(priceRepository.findByBrandIdAndProductIdAndDate(brandId, productId, applicationDate)).thenReturn(Optional.empty());

        assertThrows(PriceNotFoundException.class, () -> priceService.execute(brandId, productId, applicationDate));
    }
}