package com.ariamath.shopsmart.repository;

import java.util.List;
import java.util.Optional;

import com.ariamath.shopsmart.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface CommentRepository extends JpaRepository<Comment, Long>{

	@Query(value = "SELECT c FROM Comment c WHERE c.user_id = :userId AND c.product_id = :productId", nativeQuery = true)
	List<Comment> findByUserIdAndProductId(@Param("userId")Long user_id, @Param("productId")Long product_id);

	@Query(value = "SELECT c FROM Comment c WHERE c.user_id = :userId", nativeQuery = true)
	List<Comment> findByUserId(@Param("userId")Long user_id);

	@Query(value = "SELECT c FROM Comment c WHERE c.product_id = :productId", nativeQuery = true)
	List<Comment> findByProductId(@Param("productId")Long product_id);

}