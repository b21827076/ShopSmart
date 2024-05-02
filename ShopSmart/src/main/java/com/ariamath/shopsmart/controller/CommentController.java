package com.ariamath.shopsmart.controller;

import java.util.List;
import java.util.Optional;

import com.ariamath.shopsmart.entity.Comment;
import com.ariamath.shopsmart.request.CommentCreateRequest;
import com.ariamath.shopsmart.request.CommentUpdateRequest;
import com.ariamath.shopsmart.response.CommentResponse;
import com.ariamath.shopsmart.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/comments")
@RequiredArgsConstructor
public class CommentController {
	
	private CommentService commentService;

	@Autowired
	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}
	
	@GetMapping
	public ResponseEntity<List<CommentResponse>> getAllComments(@RequestParam Optional<Long> userId,
															   @RequestParam Optional<Long> productId) {
		return new ResponseEntity<>(commentService.getAllCommentsWithParam(userId, productId), HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<CommentResponse> createOneComment(@RequestBody CommentCreateRequest request) {
		return commentService.createOneComment(request);
	}
	
	@GetMapping("/{commentId}")
	public ResponseEntity<Comment> getOneComment(@PathVariable Long commentId) {
		return new ResponseEntity<>(commentService.getOneCommentById(commentId),HttpStatus.OK);
	}
	
	@PutMapping()
	public ResponseEntity updateOneComment(@RequestBody CommentUpdateRequest request) {
		return commentService.updateOneCommentById(request);
	}
	
	@DeleteMapping("/{commentId}")
	public ResponseEntity deleteOneComment(@PathVariable Long commentId) {
		return commentService.deleteOneCommentById(commentId);
	}
}
