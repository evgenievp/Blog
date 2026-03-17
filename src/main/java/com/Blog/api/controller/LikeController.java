package com.Blog.api.controller;

import com.Blog.api.dto.UserDto;
import com.Blog.api.model.Users;
import com.Blog.api.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/likes")
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;

    @PostMapping("/posts/{postId}/toggle")
    public ResponseEntity<LikeResponse> toggleLike(@PathVariable int postId) {
        Users currentUser = getCurrentUser();

        LikeResponse reponse = likeService.toggleLike(postId, currentUser);

        return ResponseEntity.ok(response);
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
