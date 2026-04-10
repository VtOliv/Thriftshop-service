package com.shop.service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.service.domain.DTO.UserDTO;
import com.shop.service.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
	
	private final UserService userService;

	@PostMapping("/register")
	public ResponseEntity<Void> registerUser(@RequestBody UserDTO userDTO) {
		
		log.info("Registrando usuário: {}", userDTO.getUsername());
		
		var user = userService.createUser(userDTO);
		
		log.info("Usuário registrado com sucesso: {}", user.getUsername());
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}
