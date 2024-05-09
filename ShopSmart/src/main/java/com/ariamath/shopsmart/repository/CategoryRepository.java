package com.ariamath.shopsmart.repository;

import com.ariamath.shopsmart.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<Category, Long>{

	@Query(value = "SELECT c FROM zzCategory c WHERE c.title = :categoryTitle", nativeQuery = true)
	Category findByTitle(@Param("categoryTitle") String categoryTitle);
	
}