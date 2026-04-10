package com.shop.service.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value; // Para pegar a chave do properties

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

    @Value("${app.admin.key:CHAVE_PADRAO_SEGURA}") 
    private String secretAdminKey;

    public User createUser(UserDTO userDTO) {
        
        var encryptedPassword = passwordEncoder.encode(userDTO.getPassword());
        
        var definedRole = "CLIENTE";
        if (userDTO.getAdminKey() != null && userDTO.getAdminKey().equals(secretAdminKey)) {
            definedRole = "FUNCIONARIO";
        }

        var user = User.builder()
                .username(userDTO.getUsername())
                .email(userDTO.getEmail())
                .cpf(userDTO.getCpf())
                .password(encryptedPassword)
                .role(definedRole) // O sistema decide a Role
                .build();
        
        return userRepository.save(user);
    }
    
    public LoginResponse login(LoginRequest loginRequest) {

        var user = userRepository.findByEmail(loginRequest.email())
                .orElseThrow(() -> new RuntimeException("Usuário ou senha inválidos"));

        if (!passwordEncoder.matches(loginRequest.password(), user.getPassword())) {
            throw new RuntimeException("Usuário ou senha inválidos");
        }

        var token = jwtService.generateToken(user.getEmail());

        return new LoginResponse(token, user.getRole());
    }
}