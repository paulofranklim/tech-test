package com.paulofranklim.inditex.test.infrastructure.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface JpaPriceRepository extends JpaRepository<PriceEntity, Long> {

    @Query("SELECT p FROM PriceEntity p " +
            "WHERE p.brandId = :brandId " +
            "AND p.productId = :productId " +
            "AND :applicationDate BETWEEN p.startDate AND p.endDate " +
            "ORDER BY p.priority DESC")
    List<PriceEntity> findApplicablePrices(Long brandId, Long productId, LocalDateTime applicationDate, Pageable pageable);
}
