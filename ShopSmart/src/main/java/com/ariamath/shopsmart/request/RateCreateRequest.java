package com.ariamath.shopsmart.request;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RateCreateRequest {

    Long userId;

    Long productId;

    Long rate;
}
