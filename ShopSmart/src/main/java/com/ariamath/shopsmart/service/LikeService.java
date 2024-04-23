package com.ariamath.shopsmart.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

	public Like createOneLike(LikeCreateRequest likeCreateRequest) {
		User user = userService.getOneUserById(likeCreateRequest.getUserId());
		Product product = productService.getOneProductById(likeCreateRequest.getProductId());
		if(user != null && product != null) {
			Like likeToSave = new Like();
			likeToSave.setId(likeCreateRequest.getId());
			likeToSave.setProduct(product);
			likeToSave.setUser(user);
			return likeRepository.save(likeToSave);
		}else		
			return null;
	}

	public Like getOneLikeById(Long likeId) {
		return likeRepository.findById(likeId).orElse(null);
	}

	public void deleteOneLikeById(Long likeId) {
		likeRepository.deleteById(likeId);
	}
	
	
	
	
	
	
	
}