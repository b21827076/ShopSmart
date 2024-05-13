package com.ariamath.shopsmart.service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import com.ariamath.shopsmart.entity.Comment;
import com.ariamath.shopsmart.entity.Product;
import com.ariamath.shopsmart.entity.User;
import com.ariamath.shopsmart.repository.CommentRepository;
import com.ariamath.shopsmart.request.CommentCreateRequest;
import com.ariamath.shopsmart.request.CommentUpdateRequest;
import com.ariamath.shopsmart.response.CommentResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
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
		if(user_id.isPresent()) {
			comments = commentRepository.findByUserId(user_id.get());
		}
		else if(product_id.isPresent()) {
			comments = commentRepository.findByProductId(product_id.get());
		}
		else
			comments = commentRepository.findAll();
		return comments.stream().map(CommentResponse::new).collect(Collectors.toList());
	
	}
	
	public Comment getOneCommentById(Long commentId) {
		return commentRepository.findById(commentId).orElse(null);
	}

	public ResponseEntity<CommentResponse> createOneComment(CommentCreateRequest request) {
		User user = userService.getOneUserById(request.getUserId());
		Product product = productService.getOneProductById(request.getProductId());
		if(user==null){
			log.info("user bulunamadı");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else if (product == null) {
			log.info("product bulunamadı");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else if (Objects.equals(product.getUser().getId(), user.getId())) {
			log.info("kendi ürününe yorum yapamazsın");
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		} else if (commentRepository.IsThereAComment(product.getId(),user.getId()) == 1) {
			log.info("Aynı producta 1'den fazla yorum yapamazsın");
			return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
		}else {
			Comment commentToSave = new Comment();
			commentToSave.setProduct(product);
			commentToSave.setUser(user);
			commentToSave.setContent(request.getText());
			commentToSave.setCreatedDate(new Date());
			commentToSave.setModifiedDate(new Date());
			commentRepository.save(commentToSave);
			log.info("Comment olusturuldu");
			return new ResponseEntity<>(new CommentResponse(commentToSave),HttpStatus.CREATED);
		}
	}

	public ResponseEntity updateOneCommentById(CommentUpdateRequest request) {
		Optional<Comment> comment = commentRepository.findById(request.getId());
		if(comment.isPresent()) {
			Comment commentToUpdate = comment.get();
			commentToUpdate.setContent(request.getText());
			commentToUpdate.setModifiedDate(new Date());
			commentRepository.save(commentToUpdate);
			log.info("Yorum update edildi");
			return new ResponseEntity(HttpStatus.OK);
		}else
			log.info("Product bulunamadı");
			return new ResponseEntity(HttpStatus.BAD_GATEWAY);
	}

	public ResponseEntity deleteOneCommentById(Long commentId) {
		commentRepository.deleteById(commentId);
		return new ResponseEntity(HttpStatus.OK);
	}

    public Long getCommentCountByProductId(Long id) {
		return commentRepository.countByProductId(id);
    }
	
}