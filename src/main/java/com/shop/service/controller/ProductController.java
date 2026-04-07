package com.shop.service.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.service.domain.Product;
import com.shop.service.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
	
	private final ProductService productService;
	
	@GetMapping
	public ResponseEntity<List<Product>> getAllProducts() {
		
		log.info("Received request to get all products");
		
		var products = productService.getAllProducts();
		
		log.info("Returning {} products", products.size());
		
		return ResponseEntity.ok(products);
	}

}
