package com.paulofranklim.inditex.test.infrastructure.api.v1.controller;

import com.paulofranklim.inditex.test.domain.model.Price;
import com.paulofranklim.inditex.test.domain.usecase.GetPriceUseCase;
import com.paulofranklim.inditex.test.infrastructure.api.v1.dto.PriceResponseDTO;
import com.paulofranklim.inditex.test.infrastructure.exception.PriceNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
public class PriceController {

    private final GetPriceUseCase getPriceUseCase;

    @GetMapping
    @Operation(summary = "Price check by date, product and brand")
    public ResponseEntity<PriceResponseDTO> getPrice(
            @RequestParam("applicationDate")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            @Parameter(example = "2020-06-14T16:00:00") LocalDateTime applicationDate,

            @RequestParam("productId")
            @Parameter(example = "35455") Long productId,

            @RequestParam("brandId")
            @Parameter(example = "1") Long brandId
    ) {
        Price price = getPriceUseCase.execute(brandId, productId, applicationDate)
                                     .orElseThrow(() -> new PriceNotFoundException(brandId, productId, applicationDate));

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
