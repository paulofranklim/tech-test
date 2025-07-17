package com.paulofranklim.inditex.test.infrastructure.api.v1.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PriceResponseDTO {
    @Schema(example = "35455")
    private Long productId;

    @Schema(example = "1")
    private Long brandId;

    @Schema(example = "2")
    private Integer priceList;

    @Schema(example = "2020-06-14T15:00:00")
    private LocalDateTime startDate;

    @Schema(example = "2020-06-14T18:30:00")
    private LocalDateTime endDate;

    @Schema(example = "25.45")
    private BigDecimal price;

    @Schema(example = "EUR")
    private String currency;
}

