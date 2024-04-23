package com.ariamath.shopsmart.controller;

import java.util.List;

import com.ariamath.shopsmart.entity.Category;
import com.ariamath.shopsmart.entity.SubCategory;
import com.ariamath.shopsmart.service.CategoryService;
import com.ariamath.shopsmart.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import lombok.RequiredArgsConstructor;
@RestController
@RequestMapping(path = "/api/category") 
@RequiredArgsConstructor
public class CategoryController{
	
	private CategoryService categoryService;
	private SubCategoryService subcategoryservice;
	
	@Autowired
	public CategoryController(CategoryService categoryService,
								SubCategoryService subcategoryservice) {
		this.categoryService = categoryService;
		this.subcategoryservice = subcategoryservice;
	}
	
	@GetMapping
	public List<Category> getAllCategories(){
		return categoryService.getAllCategories();
		
	}
	
	@GetMapping("/{category_id}")
	public List<	SubCategory> getOneCategoryWithSubCategories(@PathVariable Long category_id){
		return subcategoryservice.getSubCategories(category_id);
	}
	
}