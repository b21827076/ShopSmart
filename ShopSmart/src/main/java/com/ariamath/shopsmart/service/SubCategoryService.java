package com.ariamath.shopsmart.service;

import java.util.List;

import com.ariamath.shopsmart.entity.SubCategory;
import com.ariamath.shopsmart.repository.SubCategoryRepository;
import org.springframework.stereotype.Service;
@Service
public class SubCategoryService {
	
	private SubCategoryRepository subcategoryrepository;

	public SubCategoryService(SubCategoryRepository subcategoryrepository) {
		this.subcategoryrepository = subcategoryrepository;
	}

	public SubCategory getOneSubCategoryByTitle(String title) {
		return subcategoryrepository.getOneSubCategoryByTitle(title);
	}

	public List<SubCategory> getSubCategories(Long category_id) {
		return subcategoryrepository.getSubCategories(category_id);
	}

}
