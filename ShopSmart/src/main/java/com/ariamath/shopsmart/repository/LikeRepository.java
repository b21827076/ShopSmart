package com.ariamath.shopsmart.repository;

import java.util.List;

import com.ariamath.shopsmart.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface LikeRepository extends JpaRepository<Like, Long>{
	
	@Query(value = "SELECT c FROM Like c WHERE c.user_id = :userId AND c.product_id = :productId", nativeQuery = true)
	List<Like> findByUserIdAndProductId(@Param("userId") Long userId,@Param("productId") Long productId);
	
	@Query(value = "SELECT c FROM Like c WHERE c.user_id = :userId", nativeQuery = true)
	List<Like> findByUserId(@Param("userId") Long userId);

	@Query(value = "SELECT c FROM Like c WHERE c.product_id = :productId", nativeQuery = true)
	List<Like> findByProductId(@Param("productId") Long productId);
	
}