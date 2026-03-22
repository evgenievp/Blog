package com.Blog.api.controller;

import com.Blog.api.dto.UserDto;
import com.Blog.api.service.AuthService;
import com.Blog.api.service.LikeService;
import com.Blog.api.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/likes")
public class LikeController {
    private final LikeService likeService;
    private final PostService postService;
    private final AuthService authService;

    public LikeController(PostService postService,
                          LikeService likeService,
                          AuthService authService) {
        this.postService = postService;
        this.likeService = likeService;
        this.authService = authService;
    }

    @PostMapping("/like/{postId}")
    public ResponseEntity<String> like(@PathVariable int postId,
                                       Principal principal) {
        String username = principal.getName();
        this.likeService.likeOrUnlike(username, postId);
        return ResponseEntity.status(200).body("ok");
    }

    @GetMapping("/posts/{postId}/count")
    public ResponseEntity<Integer> getLikesCount(@PathVariable int postId) {
        return ResponseEntity.ok(likeService.getLikesCount(postId));
    }

    @GetMapping("/posts/{postId}/users")
    public ResponseEntity<List<UserDto>> getUsersWhoLiked(@PathVariable int postId) {
        return ResponseEntity.ok(likeService.getUsersWhoLiked(postId));
    }

}
