package com.shop.service.domain;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "product")
public class Product {
	
	@Id
	private String id;
	
	private String name;
	
	private String description;
	
	private Double price;
	
	private boolean available;
	
	private String size;
	
	private List<String> imageUrls;
	
	private Long categoryId;
}
