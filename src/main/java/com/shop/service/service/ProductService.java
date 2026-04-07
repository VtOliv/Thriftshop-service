package com.shop.service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shop.service.domain.Product;
import com.shop.service.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;
	
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}
}
