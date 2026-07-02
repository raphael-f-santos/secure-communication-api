package br.com.raphaelsantos.auth_service.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.raphaelsantos.auth_service.dto.LoginRequestDTO;
import br.com.raphaelsantos.auth_service.dto.LoginResponseDTO;
import br.com.raphaelsantos.auth_service.dto.RegisterRequestDTO;
import br.com.raphaelsantos.auth_service.dto.UserResponseDTO;
import br.com.raphaelsantos.auth_service.entity.AppUser;
import br.com.raphaelsantos.auth_service.repository.UserRepository;

@Service
public class AuthService {
    
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private JwtService jwtService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public UserResponseDTO register(RegisterRequestDTO request) {

        if (userRepository.findByUsername(request.username()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        
        AppUser user = new AppUser();

        user.setUsername(request.username());
        user.setPassword(
            passwordEncoder.encode(request.password())
        );
        
        AppUser savedUser = userRepository.save(user);

        return new UserResponseDTO(
            savedUser.getId(),
            savedUser.getUsername()
        );
    }

    public LoginResponseDTO login(LoginRequestDTO request) {

        AppUser user = userRepository
            .findByUsername(request.username())
            .orElseThrow(
                () -> new RuntimeException("User not found")
            );

        if (!passwordEncoder.matches(
            request.password(), 
            user.getPassword()
        )) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtService.generateToken(user);

        return new LoginResponseDTO(token);
    }
}
