package com.ariamath.shopsmart.request;

import lombok.Data;

@Data
public class ShoppingCartCreateRequest {

    Long userId;
    Long productId;
    Long stock;
    Long price;
}
