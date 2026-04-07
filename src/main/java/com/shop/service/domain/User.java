package com.shop.service.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document(collection = "user")
public class User {

	private String id;
	
	private String username;
	
	private String email;
	
	private String password;
	
	private String role;
}
