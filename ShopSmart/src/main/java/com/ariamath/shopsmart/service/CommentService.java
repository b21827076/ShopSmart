package com.ariamath.shopsmart.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.ariamath.shopsmart.entity.Comment;
import com.ariamath.shopsmart.entity.Product;
import com.ariamath.shopsmart.entity.User;
import com.ariamath.shopsmart.repository.CommentRepository;
import com.ariamath.shopsmart.request.CommentCreateRequest;
import com.ariamath.shopsmart.request.CommentUpdateRequest;
import com.ariamath.shopsmart.response.CommentResponse;
import org.springframework.stereotype.Service;

@Service
public class CommentService{

	
	private CommentRepository commentRepository;
	private UserService userService;
	private ProductService productService;
	
	
	public CommentService(CommentRepository commentRepository,
							UserService userService,
							ProductService productService) {
		this.commentRepository = commentRepository;
		this.userService = userService;
		this.productService = productService;
	}


	public List<CommentResponse> getAllCommentsWithParam(Optional<Long> user_id, Optional<Long> product_id) {
		List<Comment> comments;
		if(user_id.isPresent() && product_id.isPresent()) {
			comments = commentRepository.findByUserIdAndProductId(user_id.get() ,product_id.get());
		}
		else if(user_id.isPresent()) {
			comments = commentRepository.findByUserId(user_id.get());
		}else if(product_id.isPresent()) {
			comments = commentRepository.findByProductId(product_id.get());
		}else
			comments = commentRepository.findAll();
		return comments.stream().map(comment -> new CommentResponse(comment)).collect(Collectors.toList());
	
	}
	
	public Comment getOneCommentById(Long commentId) {
		return commentRepository.findById(commentId).orElse(null);
	}

	public Comment createOneComment(CommentCreateRequest request) {
		User user = userService.getOneUserById(request.getUserId());
		Product product = productService.getOneProductById(request.getProductId());
		if(user != null && product != null) {
			Comment commentToSave = new Comment();
			commentToSave.setId(request.getId());
			commentToSave.setProduct(product);
			commentToSave.setUser(user);
			commentToSave.setContent(request.getText());
			commentToSave.setCreatedDate(new Date());
			commentToSave.setModifiedDate(new Date());
			return commentRepository.save(commentToSave);
		}else		
			return null;
	}

	public Comment updateOneCommentById(Long commentId, CommentUpdateRequest request) {
		Optional<Comment> comment = commentRepository.findById(commentId);
		if(comment.isPresent()) {
			Comment commentToUpdate = comment.get();
			commentToUpdate.setContent(request.getText());
			return commentRepository.save(commentToUpdate);
		}else
			return null;
	}

	public void deleteOneCommentById(Long commentId) {
		commentRepository.deleteById(commentId);
	}
	
}