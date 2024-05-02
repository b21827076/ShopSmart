package com.ariamath.shopsmart.controller;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.ariamath.shopsmart.entity.Like;
import com.ariamath.shopsmart.repository.ProductRepository;
import com.ariamath.shopsmart.repository.UserRepository;
import com.ariamath.shopsmart.request.LikeCreateRequest;
import com.ariamath.shopsmart.response.LikeResponse;
import com.ariamath.shopsmart.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/likes") //localhost::api/likes
@RequiredArgsConstructor
public class LikeController {
	@Autowired
	private LikeService likeService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ProductRepository productRepository;

	public LikeController(LikeService likeService, ProductRepository productRepository, UserRepository userRepository) {
		this.likeService = likeService;
		this.productRepository = productRepository;
		this.userRepository = userRepository;
	}

	@GetMapping
	public ResponseEntity<List<LikeResponse>> getAllLikes(@RequestParam Optional<Long> userId, @RequestParam Optional<Long> productId) {
		return new ResponseEntity<>(likeService.getAllLikesWithParam(userId, productId),HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<LikeResponse> createOneLike(@RequestBody LikeCreateRequest likeCreateRequest) {
		if(userRepository.findById(likeCreateRequest.getUserId()).isEmpty()){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else if (productRepository.findById(likeCreateRequest.getProductId()).isEmpty()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		List<LikeResponse> likes = likeService.getLikesByUserId(likeCreateRequest.getUserId());
		for(LikeResponse x :likes){
			if(Objects.equals(x.getProductId(), likeCreateRequest.getProductId())){
				return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
			}
		}
		return new ResponseEntity<>(likeService.createOneLike(likeCreateRequest),HttpStatus.CREATED);
	}
	
	@GetMapping("/{likeId}")
	public ResponseEntity<LikeResponse> getOneLike(@PathVariable Long likeId) {
		return new ResponseEntity<>(likeService.getOneLikeById(likeId), HttpStatus.OK);
	}
	
	@DeleteMapping("/{likeId}")
	public ResponseEntity<HttpStatus> deleteOneLike(@PathVariable Long likeId) {
		likeService.deleteOneLikeById(likeId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	//CreateRequest'te gerekli bilgiler olduğu için extra LikeDelRequest oluşturmadım.
	@DeleteMapping()
	public ResponseEntity<HttpStatus> deleteOneLikeByRequest(@RequestBody LikeCreateRequest likeDeleteRequest) {
		likeService.deleteOneLikeByRequest(likeDeleteRequest);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}