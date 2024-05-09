package com.ariamath.shopsmart.service;

import java.util.List;

import com.ariamath.shopsmart.entity.Category;
import com.ariamath.shopsmart.repository.CategoryRepository;
import org.springframework.stereotype.Service;
@Service
public class CategoryService{
	
	private CategoryRepository categoryRepository;

	public CategoryService(CategoryRepository CategoryRepository) {
		this.categoryRepository = CategoryRepository;
	}

	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}
	
	
	
}