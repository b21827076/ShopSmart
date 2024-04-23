package com.ariamath.shopsmart.controller;

import java.util.List;
import java.util.Optional;

import com.ariamath.shopsmart.entity.Like;
import com.ariamath.shopsmart.request.LikeCreateRequest;
import com.ariamath.shopsmart.response.LikeResponse;
import com.ariamath.shopsmart.service.LikeService;
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
	
	private LikeService likeService;

	public LikeController(LikeService likeService) {
		this.likeService = likeService;
	}
	
	@GetMapping
	public List<LikeResponse> getAllLikes(@RequestParam Optional<Long> userId, @RequestParam Optional<Long> productId) {
		return likeService.getAllLikesWithParam(userId, productId);
	}
	
	@PostMapping
	public Like createOneLike(@RequestBody LikeCreateRequest likeCreateRequest) {
		return likeService.createOneLike(likeCreateRequest);
	}
	
	@GetMapping("/{likeId}")
	public Like getOneLike(@PathVariable Long likeId) {
		return likeService.getOneLikeById(likeId);
	}
	
	@DeleteMapping("/{likeId}")
	public void deleteOneLike(@PathVariable Long likeId) {
		likeService.deleteOneLikeById(likeId);
	}
	
	
}