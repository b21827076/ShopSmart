package com.ariamath.shopsmart.repository;

import com.ariamath.shopsmart.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query(value = "SELECT c.* FROM Product c WHERE c.subcategory_id = :subcategory_id", nativeQuery = true)
	List<Product> findBySubCategoryId(Long subcategory_id);


	
}