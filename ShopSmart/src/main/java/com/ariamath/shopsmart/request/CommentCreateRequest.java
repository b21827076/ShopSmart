package com.ariamath.shopsmart.request;

import lombok.Data;

@Data
public class CommentCreateRequest{
	
	Long id;
	Long userId;
	Long productId;
	String text;
}
