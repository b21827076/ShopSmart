package com.ariamath.shopsmart.request;

import lombok.Data;

@Data
public class LikeCreateRequest {

	Long userId;
	Long productId;
	
}