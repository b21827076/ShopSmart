package com.ariamath.shopsmart.request;

import com.ariamath.shopsmart.entity.SubCategory;
import com.ariamath.shopsmart.entity.User;
import lombok.Data;

import java.util.Date;

@Data
public class ProductUpdateRequest {

	String name;
	String description;
	Long price;
	String subcategory_name;
	String imgUrl;
	Long stock;
	String user_name;

	public ProductUpdateRequest(String name, String description, Long price, String subcategory_name, String imgUrl, Long stock,String user_name) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.subcategory_name = subcategory_name;
		this.imgUrl = imgUrl;
		this.stock = stock;
		this.user_name = user_name;
	}
}