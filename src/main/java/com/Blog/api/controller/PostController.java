package com.Blog.api.controller;

import com.Blog.api.dto.PostDto;
import com.Blog.api.service.AuthService;
import com.Blog.api.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
                                        @PathVariable int authorId) {
        this.authService.findById(authorId);
        PostDto postDto = this.postService.post(authorId, post);
        return ResponseEntity.status(201).body(postDto);
    }



}
