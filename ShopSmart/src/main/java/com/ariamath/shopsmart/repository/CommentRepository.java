package com.ariamath.shopsmart.repository;

import java.util.List;
import java.util.Optional;

import com.ariamath.shopsmart.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface CommentRepository extends JpaRepository<Comment, Long>{

	@Query(value = "SELECT c FROM Comment c WHERE c.user.id = :userId AND c.product.id = :productId")
	Comment findByUserIdAndProductId(@Param("userId")Long userId, @Param("productId")Long productId);

	@Query(value = "SELECT c FROM Comment c WHERE c.user.id = :userId")
	List<Comment> findByUserId(@Param("userId")Long userId);

	@Query(value = "SELECT c FROM Comment c WHERE c.product.id = :productId")
	List<Comment> findByProductId(@Param("productId")Long productId);

	@Query(value = "SELECT c FROM Comment c WHERE c.id = :commentId")
	Optional<Comment> findById(@Param("commentId")Long commentId);


	@Query(value = "SELECT COUNT(c) FROM Comment c WHERE c.product.id = :id")
    Long countByProductId(Long id);
}