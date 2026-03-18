package com.Blog.api.controller;

import com.Blog.api.model.Users;
import com.Blog.api.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public String login() {
//        this.authService.login();
        return "logged in";
    }

    @PostMapping("/register")
    public ResponseEntity<Users> register() {
        Users user = new Users();
        this.authService.save(user);
        return ResponseEntity.status(201).body(user);
    }

}
