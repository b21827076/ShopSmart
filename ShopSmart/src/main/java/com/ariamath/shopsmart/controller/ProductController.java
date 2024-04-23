package com.ariamath.shopsmart.controller;
import com.ariamath.shopsmart.entity.Product;
import com.ariamath.shopsmart.request.ProductCreateRequest;
import com.ariamath.shopsmart.request.ProductUpdateRequest;
import com.ariamath.shopsmart.service.ProductService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path = "/api/product") 
@RequiredArgsConstructor
public class ProductController {

	private ProductService productService;

	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping
	public List<Product> getAllProducts() {
		return productService.getAllProducts();
	}

	@GetMapping("/{product_id}")
	public Optional<Product> getOneProduct(@PathVariable Long product_id) {
		return productService.getOneProduct(product_id);
	}

	@PostMapping()
	public void createNewProduct(@RequestBody ProductCreateRequest newProductCreateRequest) {
		productService.createNewProduct(newProductCreateRequest);
	}

	@PutMapping("/{productId}")
	public void updateOneProduct(@PathVariable Long productId, @RequestBody ProductUpdateRequest updateProduct) {
		productService.updateOneProductById(productId, updateProduct);
	}

	@DeleteMapping("/{productId}")
	public void deleteOneProduct(@PathVariable Long productId) {
		productService.deleteOneProductById(productId);
	}

}