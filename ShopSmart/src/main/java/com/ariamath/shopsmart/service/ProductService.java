package com.ariamath.shopsmart.service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.ariamath.shopsmart.entity.Product;
import com.ariamath.shopsmart.entity.SubCategory;
import com.ariamath.shopsmart.entity.User;
import com.ariamath.shopsmart.exception.PermissionDenied;
import com.ariamath.shopsmart.repository.ProductRepository;
import com.ariamath.shopsmart.request.ProductCreateRequest;
import com.ariamath.shopsmart.request.ProductUpdateRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProductService {
	
	private ProductRepository productRepository;
	private UserService userService;
	private SubCategoryService subcategoryService;
	private LikeService likeService;
	
	public ProductService(ProductRepository ProductRepository,
							UserService userService,
							SubCategoryService subcategoryService,
							LikeService likeService) {
		
		this.productRepository = ProductRepository;
		this.subcategoryService = subcategoryService;
		this.userService = userService;
		this.likeService = likeService;
	}

	
	public void createNewProduct(ProductCreateRequest newProductCreateRequest) {
		if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("Merchant"))) {
			String user_name = SecurityContextHolder.getContext().getAuthentication().getName();
			User user = userService.getOneUserByUserName(newProductCreateRequest.getUser_name());
			SubCategory subcategory = subcategoryService.getOneSubCategoryByTitle(newProductCreateRequest.getSubcategory());
			if(subcategory == null){
				log.info("Subcategory Bulunamadı");
			} else if (!user_name.equals(newProductCreateRequest.getUser_name())) {
				log.info("Başkası adına urun ekleme yapamazsınız");
			} else{
				Product toSave = new Product();
				toSave.setUser(user);
				toSave.setSubcategory(subcategory);
				toSave.setCreatedDate(new Date());
				toSave.setDescription(newProductCreateRequest.getContent());
				toSave.setImg_url(newProductCreateRequest.getPhotolink());
				toSave.setModifiedDate(new Date());
				toSave.setName(newProductCreateRequest.getTitle());
				toSave.setPrice(newProductCreateRequest.getPrice());
				toSave.setStock(newProductCreateRequest.getStock());
				productRepository.save(toSave);
				log.info("Merchant urun ekledi! "+(HttpStatus.OK));
			}
		}
		else if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("Admin"))) {
			User user = userService.getOneUserByUserName(newProductCreateRequest.getUser_name());
			SubCategory subcategory = subcategoryService.getOneSubCategoryByTitle(newProductCreateRequest.getSubcategory());
			if(subcategory == null){
				log.info("Subcategory Bulunamadı");
			} else if (!user.getRole_name().equals("Merchant")) {
				log.info("Sadece Merchantlara urun eklemesi yapabilirsiniz !");
			} else{
				Product toSave = new Product();
				toSave.setUser(user);
				toSave.setSubcategory(subcategory);
				toSave.setCreatedDate(new Date());
				toSave.setDescription(newProductCreateRequest.getContent());
				toSave.setImg_url(newProductCreateRequest.getPhotolink());
				toSave.setModifiedDate(new Date());
				toSave.setName(newProductCreateRequest.getTitle());
				toSave.setPrice(newProductCreateRequest.getPrice());
				toSave.setStock(newProductCreateRequest.getStock());
				productRepository.save(toSave);
				log.info("Admin urun ekledi! "+ HttpStatus.OK);
			}

		}
		else{
			log.info(new PermissionDenied(SecurityContextHolder.getContext().getAuthentication().getName()).getError());
		}
	}

	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	public void updateOneProductById(Long productId, ProductUpdateRequest updateProduct) {
		if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("Merchant"))){
		Product product = productRepository.getById(productId);
		SubCategory subCategory = subcategoryService.getOneSubCategoryByTitle(updateProduct.getSubcategory_name());
		if(product==null){
			log.info("Product Bulunamadı");
		} else if (!Objects.equals(product.getUser().getUser_name(), updateProduct.getUser_name())){
			log.info("Sadece kendi urunlerini değiştirebilirsin!");
		} else if (subCategory == null) {
			log.info("Mevcut bir subcategory ile değiştirebilirsin!");
		} else{
			product.setName(updateProduct.getName());
			product.setPrice(updateProduct.getPrice());
			product.setDescription(updateProduct.getDescription());
			product.setStock(updateProduct.getStock());
			product.setModifiedDate(new Date());
			product.setSubcategory(subCategory);
			product.setImg_url(updateProduct.getImgUrl());

			productRepository.save(product);
			log.info("Merchant tarafından urun değiştirilmiştir!");
		}
		} else if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("Admin"))) {
			Product product = productRepository.getById(productId);
			User user = userService.getOneUserByUserName(updateProduct.getUser_name());
			SubCategory subCategory = subcategoryService.getOneSubCategoryByTitle(updateProduct.getSubcategory_name());
			if(product==null) {
				log.info("Product Bulunamadı");
			} else if (user == null) {
				log.info("User Bulunamadı");
			} else if (!Objects.equals(user.getRole_name(),"Merchant")) {
				log.info("User'ın Merchant olması gerekmektedir");
			} else if (subCategory == null) {
				log.info("Mevcut bir subcategory ile değiştirebilirsin!");
			} else {
				product.setName(updateProduct.getName());
				product.setUser(user);
				product.setPrice(updateProduct.getPrice());
				product.setDescription(updateProduct.getDescription());
				product.setStock(updateProduct.getStock());
				product.setModifiedDate(new Date());
				product.setSubcategory(subCategory);
				product.setImg_url(updateProduct.getImgUrl());

				productRepository.save(product);
				log.info("Admin tarafından urun değiştirilmiştir!");
			}
		}else{
			log.info(new PermissionDenied(SecurityContextHolder.getContext().getAuthentication().getName()).getError());
		}
	}

	public void deleteOneProductById(Long productId) {
		if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("Merchant"))){
		Product product = productRepository.getById(productId);
		Long userId = userService.getOneUserByUserName(SecurityContextHolder.getContext().getAuthentication().getName()).getId();
		if(product==null){
			log.info("böyle bi urun bulunmamaktadır");
		}
		else if(!Objects.equals(userId, product.getId())){
			log.info("Sadece kendi urununu silebilirsin");
		}else{
			productRepository.delete(product);
			log.info("Merchant tarafından urun silinmistir");
			}
		}
		else if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("Admin"))) {
			Product product = productRepository.getById(productId);
			if(product==null){
				log.info("böyle bi urun bulunmamaktadır");
			}else{
				productRepository.delete(product);
				log.info("Admin tarafından urun silinmistir");
			}
		}
		else{
			log.info(new PermissionDenied(SecurityContextHolder.getContext().getAuthentication().getName()).getError());
		}
	}

	public Product getOneProductById(Long productId) {
		return productRepository.findById(productId).orElse(null);
	}
	
	public Optional<Product> getOneProduct(Long product_id) {
		return productRepository.findById(product_id);
	}


	public List<Product> getAllProductsBySubCategoryId(Long subcategory_id) {
		return productRepository.findBySubCategoryId(subcategory_id);
	}
	
}
