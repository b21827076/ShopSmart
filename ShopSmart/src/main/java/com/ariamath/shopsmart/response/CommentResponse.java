package com.ariamath.shopsmart.response;

import com.ariamath.shopsmart.entity.Comment;
import lombok.Data;

@Data
public class CommentResponse {
	
	Long id;
	Long userId;
	Long productId;
	String userName;
	String text;
	
	public CommentResponse(Comment comment) {
		this.id = comment.getId();
		this.productId = comment.getProduct().getId();
		this.userId = comment.getUser().getId();
		this.userName = comment.getUser().getUser_name();
		this.text = comment.getContent();
	}

}
