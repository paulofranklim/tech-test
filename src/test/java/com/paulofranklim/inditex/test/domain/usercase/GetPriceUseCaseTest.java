package com.paulofranklim.inditex.test.domain.usercase;

import com.paulofranklim.inditex.test.application.service.PriceService;
import com.paulofranklim.inditex.test.domain.model.Price;
import com.paulofranklim.inditex.test.domain.usecase.GetPriceUseCase;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class GetPriceUseCaseTest {

    private final PriceService service = mock(PriceService.class);
    private final GetPriceUseCase useCase = new GetPriceUseCase(service);

    @Test
    void shouldReturnPriceWhenFound() {
        Long brandId = 1L;
        Long productId = 35455L;
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 10, 0);

        Price mockPrice = new Price(brandId, LocalDateTime.now(), LocalDateTime.now()
                                                                               .plusMinutes(15), 1, productId, 1, BigDecimal.valueOf(35.50), "EUR");

        when(service.getApplicablePrice(brandId, productId, date))
                .thenReturn(Optional.of(mockPrice));

        Optional<Price> result = useCase.execute(brandId, productId, date);

        assertTrue(result.isPresent());
        assertEquals(BigDecimal.valueOf(35.50), result.get().getPrice());
        verify(service, times(1)).getApplicablePrice(brandId, productId, date);
    }

    @Test
    void shouldReturnEmptyWhenNoPriceFound() {
        Long brandId = 1L;
        Long productId = 35455L;
        LocalDateTime date = LocalDateTime.of(2022, 1, 1, 0, 0);

        when(service.getApplicablePrice(brandId, productId, date))
                .thenReturn(Optional.empty());

        Optional<Price> result = useCase.execute(brandId, productId, date);

        assertTrue(result.isEmpty());
    }
}
