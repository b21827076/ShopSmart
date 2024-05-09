package com.ariamath.shopsmart.repository;

import java.util.List;

import com.ariamath.shopsmart.entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SubCategoryRepository extends JpaRepository<SubCategory,Long> {
	
	@Query(value = "SELECT c FROM SubCategory c WHERE c.title = :categoryTitle")
	SubCategory getOneSubCategoryByTitle(@Param("categoryTitle") String title);
	
	@Query(value = "SELECT c.* FROM subcategory c WHERE c.category_id = :category_id", nativeQuery = true)
	List<SubCategory> getSubCategories(Long category_id);

}
