package com.Blog.api.service;

import com.Blog.api.dto.UserDto;
import com.Blog.api.model.Users;
import com.Blog.api.repo.AuthRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final AuthRepo authRepo;

    public AuthService(AuthRepo authRepo) {
        this.authRepo = authRepo;
    }


    public String login() {
        return "loggedin";
    }

    public String register() {
        return "registerd";
    }

    public void save(Users user) {
        this.authRepo.save(user);
    }


    public UserDto findById(int userId) {
        Optional<Users> opt = this.authRepo.findById(userId);
        if (opt.isEmpty()) {
            throw new EntityNotFoundException("no such user");
        }
        UserDto userDto = this.toDto(opt.get());
        return userDto;
     }

    private UserDto toDto(Users user) {
        return new UserDto();
    }

    public UserDto findByUsername(String username) {
        Optional<Users> opt = this.authRepo.findByUsername(username);
        if (opt.isEmpty()) {
            throw new EntityNotFoundException("no such user");
        }
        return this.toDto(opt.get());
    }
}
