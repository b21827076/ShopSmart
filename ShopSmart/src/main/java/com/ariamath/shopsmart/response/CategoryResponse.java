package com.ariamath.shopsmart.response;

import com.ariamath.shopsmart.entity.Category;
import lombok.Data;

@Data
public class CategoryResponse{
	
	Long id;
	String title;
	
	public CategoryResponse(Category category) {
		this.id = category.getId();
		this.title = category.getTitle();
	}
}