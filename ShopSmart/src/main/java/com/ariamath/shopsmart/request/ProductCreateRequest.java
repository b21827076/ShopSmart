package com.ariamath.shopsmart.request;

import lombok.Data;

@Data
public class ProductCreateRequest {

	String photolink;
	String title;
	String content;
	Long stock;
	String user_name;
	Long price;
	String subcategory;

	public ProductCreateRequest(String photolink, String title, String content, Long stock, Long price, String user_name,String subcategory) {
		this.photolink = photolink;
		this.title = title;
		this.content = content;
		this.stock = stock;
		this.price = price;
		this.user_name = user_name;
		this.subcategory = subcategory;
	}
}
