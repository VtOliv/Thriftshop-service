package com.shop.service.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.shop.service.domain.User;

public interface UserRepository extends MongoRepository<User, String> {
	
	Optional<User> findByUsername(String username);
	Optional<User> findByEmail(String email);

}
