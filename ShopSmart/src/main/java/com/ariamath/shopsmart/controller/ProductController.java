package com.ariamath.shopsmart.controller;
import com.ariamath.shopsmart.entity.Product;
import com.ariamath.shopsmart.request.ProductCreateRequest;
import com.ariamath.shopsmart.request.ProductUpdateRequest;
import com.ariamath.shopsmart.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
	public ResponseEntity<List<Product>> getAllProducts() {
		return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
	}

	@GetMapping("/{product_id}")
	public Product getOneProduct(@PathVariable Long product_id) {
		return productService.getOneProductById(product_id);
	}

	@PostMapping()
	public void createNewProduct(@RequestBody ProductCreateRequest newProductCreateRequest) {
		productService.createNewProduct(newProductCreateRequest);
	}

	@PutMapping("/{productId}")
	public ResponseEntity<HttpStatus> updateOneProduct(@PathVariable Long productId, @RequestBody ProductUpdateRequest updateProduct) {
		productService.updateOneProductById(productId, updateProduct);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/{productId}")
	public void deleteOneProduct(@PathVariable Long productId) {
		productService.deleteOneProductById(productId);
	}

	@GetMapping("/search/{keyword}")
	public ResponseEntity<List<Product>> searchProduct(@PathVariable String keyword){
		return new ResponseEntity<>(productService.searchProduct(keyword),HttpStatus.OK);
	}
}