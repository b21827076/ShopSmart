package com.ariamath.shopsmart.response;

public class SubCategoryResponse {
	
	Long id;
	String title;
	Long category_id;
	
	public SubCategoryResponse(Long id, String title, Long category_id) {
		this.id = id;
		this.title = title;
		this.category_id = category_id;
	}
	
	
}
