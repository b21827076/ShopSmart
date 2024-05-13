package com.ariamath.shopsmart.repository;

import java.util.List;

import com.ariamath.shopsmart.entity.Like;
import com.ariamath.shopsmart.response.LikeResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface LikeRepository extends JpaRepository<Like, Long>{
	
	@Query(value = "SELECT c FROM Like c WHERE c.user.id = :userId AND c.Product.id = :productId")
	List<Like> findByUserIdAndProductId(Long userId, Long productId);

	@Query(value = "SELECT c FROM Like c WHERE c.user.id = :userId")
	List<Like> findByUserId(Long userId);

	@Query(value = "SELECT c FROM Like c WHERE c.Product.id= :productId")
	List<Like> findByProductId(Long productId);

	@Query(value = "SELECT COUNT(c) FROM Like c WHERE c.Product.id = :productId")
	Long countByProductId(Long productId);

	@Query(value = "SELECT c FROM Like c WHERE c.user.id = :userId")
	List<Like> getLikesByUserId(Long userId);

}