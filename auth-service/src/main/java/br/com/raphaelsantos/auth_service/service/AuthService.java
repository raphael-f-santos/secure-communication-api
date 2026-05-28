package br.com.raphaelsantos.auth_service.service;

import org.springframework.stereotype.Service;

import br.com.raphaelsantos.auth_service.dto.RegisterRequestDTO;
import br.com.raphaelsantos.auth_service.dto.UserResponseDTO;
import br.com.raphaelsantos.auth_service.entity.AppUser;
import br.com.raphaelsantos.auth_service.repository.UserRepository;

@Service
public class AuthService {
    
    private UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDTO register(RegisterRequestDTO request) {

        if (userRepository.findByUsername(request.username()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        
        AppUser user = new AppUser();

        user.setUsername(request.username());
        user.setPassword(request.password());
        
        AppUser savedUser = userRepository.save(user);

        return new UserResponseDTO(
            savedUser.getId(),
            savedUser.getUsername()
        );
    }
}
