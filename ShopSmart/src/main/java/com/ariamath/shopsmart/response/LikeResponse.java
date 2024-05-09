package com.ariamath.shopsmart.response;

import com.ariamath.shopsmart.entity.Like;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class LikeResponse{
	
	Long id;
	Long userId;
	Long productId;

	public LikeResponse(Like like) {
		this.id = like.getId();
		this.productId = like.getProduct().getId();
		this.userId = like.getUser().getId();
	}
}