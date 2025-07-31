package com.paulofranklim.inditex.test.infrastructure.api.v1.controller;

import com.paulofranklim.inditex.test.domain.model.Price;
import com.paulofranklim.inditex.test.domain.usecase.GetPriceUseCase;
import com.paulofranklim.inditex.test.infrastructure.api.v1.generated.controller.DefaultApi;
import com.paulofranklim.inditex.test.infrastructure.api.v1.generated.model.PriceResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/prices")
@RequiredArgsConstructor
public class PriceController  implements DefaultApi {

    private final GetPriceUseCase getPriceUseCase;

    @GetMapping
    public ResponseEntity<PriceResponseDTO> getPrice(
            @RequestParam("applicationDate")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate,

            @RequestParam("productId") Long productId,

            @RequestParam("brandId") Long brandId
    ) {
        Price price = getPriceUseCase.execute(brandId, productId, applicationDate);

        PriceResponseDTO response = PriceResponseDTO.builder()
                                                    .productId(price.getProductId())
                                                    .brandId(price.getBrandId())
                                                    .priceList(price.getPriceList())
                                                    .startDate(price.getStartDate())
                                                    .endDate(price.getEndDate())
                                                    .price(price.getPrice())
                                                    .currency(price.getCurrency())
                                                    .build();

        return ResponseEntity.ok(response);
    }
}
