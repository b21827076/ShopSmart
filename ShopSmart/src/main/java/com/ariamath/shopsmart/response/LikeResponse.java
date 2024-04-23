package com.ariamath.shopsmart.response;

import com.ariamath.shopsmart.entity.Like;
import lombok.Data;

@Data
public class LikeResponse{
	
	Long id;
	Long userId;
	Long productId;
	
	public LikeResponse(Like like) {
		this.id = like.getId();
		this.userId = like.getUser().getId();
		this.productId = like.getProduct().getId();
	} 
	
}