package com.ariamath.shopsmart.service;

import com.ariamath.shopsmart.repository.ProductRepository;
import com.ariamath.shopsmart.request.RateCreateRequest;
import com.ariamath.shopsmart.request.RateUpdateRequest;
import com.ariamath.shopsmart.response.RateResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RateService {

    private ProductRepository productRepository;
    private UserService userService;

    public RateResponse getRateByProductId(Long productId) {
        return new RateResponse();
    }

    public void createNewRate(RateCreateRequest newRateCreateRequest) {
    }

    public void updateRateByProductId(Long productId, RateUpdateRequest rateUpdateRequest) {
    }

    public void deleteRateByProductId(Long productId) {
    }
}
