package com.ariamath.shopsmart.request;

import lombok.Data;

@Data
public class CommentCreateRequest{

	Long userId;
	Long productId;
	String text;
}
