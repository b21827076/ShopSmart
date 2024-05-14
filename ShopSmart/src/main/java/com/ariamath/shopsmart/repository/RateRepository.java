package com.ariamath.shopsmart.repository;

import com.ariamath.shopsmart.entity.Rate;
import com.ariamath.shopsmart.response.RateResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RateRepository extends JpaRepository<Rate,Long> {

    @Query(value = "SELECT c FROM Rate c WHERE c.product.id = :productId")
    List<Rate> getRatesByProductId(Long productId);

    @Query(value = "SELECT c FROM Rate c WHERE c.id = :rateId")
    Rate getByRateId(Long rateId);
}
