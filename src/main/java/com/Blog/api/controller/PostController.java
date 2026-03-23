package com.Blog.api.controller;

import com.Blog.api.dto.PostDto;
import com.Blog.api.service.AuthService;
import com.Blog.api.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;
    private final AuthService authService;

    public PostController(PostService postService, AuthService authService) {
        this.postService = postService;
        this.authService = authService;
    }

    @PostMapping("/post/{authorId}")
    public ResponseEntity<PostDto> post(@RequestBody PostDto post,
                                        Principal principal) {
        this.authService.findByUsername(principal.getName());
        PostDto postDto = this.postService.post(principal.getName(), post);
        return ResponseEntity.status(201).body(postDto);
    }

}
