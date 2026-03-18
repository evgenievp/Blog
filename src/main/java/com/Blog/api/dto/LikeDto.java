package com.Blog.api.dto;

import com.Blog.api.model.Post;
import com.Blog.api.model.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class LikeDto {
    private Post post;
    private Users user;
    private LocalDateTime createdAt;

}
