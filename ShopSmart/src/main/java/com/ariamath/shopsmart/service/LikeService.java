package com.ariamath.shopsmart.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.ariamath.shopsmart.entity.Like;
import com.ariamath.shopsmart.entity.Product;
import com.ariamath.shopsmart.entity.User;
import com.ariamath.shopsmart.repository.LikeRepository;
import com.ariamath.shopsmart.request.LikeCreateRequest;
import com.ariamath.shopsmart.response.LikeResponse;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
@Service
public class LikeService{
	
	private LikeRepository likeRepository;
	private UserService userService;
	private ProductService productService;
	
	public LikeService(LikeRepository likeRepository,
						UserService UserService,
						@Lazy ProductService ProductService) {
		this.likeRepository = likeRepository;
		this.userService = UserService;
		productService = ProductService;
	}

	public List<LikeResponse> getAllLikesWithParam(Optional<Long> userId, Optional<Long> productId) {
		List<Like> list;
		if(userId.isPresent() && productId.isPresent()) {
			list = likeRepository.findByUserIdAndProductId(userId.get(), productId.get());
		}else if(userId.isPresent()) {
			list = likeRepository.findByUserId(userId.get());
		}else if(productId.isPresent()) {
			list = likeRepository.findByProductId(productId.get());
		}else
			list = likeRepository.findAll();
		return list.stream().map(LikeResponse::new).collect(Collectors.toList());
	}


	public LikeResponse createOneLike(LikeCreateRequest likeCreateRequest) {
		User user = userService.getOneUserById(likeCreateRequest.getUserId());
		Product product = productService.getOneProductById(likeCreateRequest.getProductId());
		if(user != null && product != null) {
			Like likeToSave = new Like();
			likeToSave.setProduct(product);
			likeToSave.setUser(user);
			return new LikeResponse(likeRepository.save(likeToSave));
		}else		
			return null;
	}

	public LikeResponse getOneLikeById(Long likeId) {
		return new LikeResponse(Objects.requireNonNull(likeRepository.findById(likeId).orElse(null)));
	}
/*
	public List<LikeResponse> getLikesByProductId(Long productId){
		return likeRepository.getLikesByProductId(productId).stream().map(LikeResponse::new).collect(Collectors.toList());
	}
 */
	public List<LikeResponse> getLikesByUserId(Long userId) {
		return likeRepository.getLikesByUserId(userId).stream().map(LikeResponse::new).collect(Collectors.toList());
	}

	public void deleteOneLikeById(Long likeId) {
		likeRepository.deleteById(likeId);
	}

	public void deleteOneLikeByRequest(LikeCreateRequest likeDeleteRequest) {
		List<Like> likes = likeRepository.findByUserIdAndProductId(likeDeleteRequest.getUserId(), likeDeleteRequest.getProductId());
		likeRepository.deleteAll(likes);
	}
}