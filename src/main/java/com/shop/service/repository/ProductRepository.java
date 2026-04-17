package com.shop.service.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.shop.service.domain.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
	
	Optional<List<Product>> findByCategory(String category);

}
