package com.shop.service.service;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shop.service.domain.User;
import com.shop.service.domain.DTO.UserDTO;
import com.shop.service.domain.record.LoginRequest;
import com.shop.service.domain.record.LoginResponse;
import com.shop.service.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
	
	public void createUser(UserDTO userDTO) {
		
		var password = BCrypt.hashpw(userDTO.getPassword(), BCrypt.gensalt());
		
		var user = User.builder()
				.username(userDTO.getUsername())
				.email(userDTO.getEmail())
				.password(password)
				.role(userDTO.getRole())
				.build();
		
		userRepository.save(user);
	}
	
	public LoginResponse login(LoginRequest loginRequest) {

        var user = userRepository.findByUsername(loginRequest.username())
                .orElseThrow(() -> new RuntimeException("Usuário ou senha inválidos"));

        if (!passwordEncoder.matches(loginRequest.password(), user.getPassword())) {
            throw new RuntimeException("Usuário ou senha inválidos");
        }

        var token = jwtService.generateToken(user.getUsername());

        return new LoginResponse(token);
    }
	
}
