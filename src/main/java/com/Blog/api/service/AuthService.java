package com.Blog.api.service;

import com.Blog.api.config.JwtService;
import com.Blog.api.dto.LoginRequest;
import com.Blog.api.dto.LoginResponse;
import com.Blog.api.dto.RegisterRequest;
import com.Blog.api.dto.UserDto;
import com.Blog.api.model.Users;
import com.Blog.api.repo.AuthRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final AuthRepo authRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(AuthRepo authRepo, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.authRepo = authRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }


    public LoginResponse login(LoginRequest request) {
        Users user = authRepo.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String accessToken = jwtService.generateAccessToken(user.getUsername());
        String refreshToken = jwtService.generateRefreshToken(user.getUsername());

        return new LoginResponse(accessToken, refreshToken);
    }


    public void register(RegisterRequest request) {
        if (authRepo.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        Users user = new Users();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        authRepo.save(user);
    }


    public UserDto findById(int userId) {
        Optional<Users> opt = this.authRepo.findById(userId);
        if (opt.isEmpty()) {
            throw new EntityNotFoundException("no such user");
        }
        UserDto userDto = this.toDto(opt.get());
        return userDto;
     }

    public UserDto toDto(Users users) {
        return new UserDto(
                users.getUsername()
        );
    }


    public UserDto findByUsername(String username) {
        Optional<Users> opt = this.authRepo.findByUsername(username);
        if (opt.isEmpty()) {
            throw new EntityNotFoundException("no such user");
        }
        return this.toDto(opt.get());
    }

}
