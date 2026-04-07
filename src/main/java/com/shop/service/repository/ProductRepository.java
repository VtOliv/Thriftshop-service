package com.shop.service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.shop.service.domain.Product;

public interface ProductRepository extends MongoRepository<Product, String> {

}
