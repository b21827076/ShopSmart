package com.ariamath.shopsmart.controller;

import java.util.List;

import com.ariamath.shopsmart.entity.Product;
import com.ariamath.shopsmart.response.ProductResponse;
import com.ariamath.shopsmart.service.ProductService;
import com.ariamath.shopsmart.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/category/{category_id}/subcategory") 
@RequiredArgsConstructor
public class SubCategoryController {

    private SubCategoryService subcategoryservice;
    private ProductService productservice;

    @Autowired
    public SubCategoryController(SubCategoryService subcategoryservice,
                                 ProductService productservice) {
        this.subcategoryservice = subcategoryservice;
        this.productservice = productservice;
    }

    @GetMapping("/{subcategory_id}")
    public ResponseEntity<List<ProductResponse>> getAllProductsBySubCategoryId(@PathVariable Long subcategory_id){
        return new ResponseEntity<>(productservice.getAllProductsBySubCategoryId(subcategory_id),HttpStatus.OK);

    }

}
