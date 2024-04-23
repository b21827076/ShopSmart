package com.ariamath.shopsmart.response;

import com.ariamath.shopsmart.entity.Comment;
import lombok.Data;

@Data
public class CommentResponse {
	
	Long id;
	Long userId;
	String userName;
	String text;
	
	public CommentResponse(Comment comment) {
		this.id = comment.getId();
		this.userId = comment.getUser().getId();
		this.userName = comment.getUser().getUser_name();
		this.text = comment.getContent();
	}

}
