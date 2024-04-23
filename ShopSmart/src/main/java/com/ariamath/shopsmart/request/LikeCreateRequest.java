package com.ariamath.shopsmart.request;

import lombok.Data;

@Data
public class LikeCreateRequest {
	
	Long id;
	Long userId;
	Long productId;
	
}