package com.Blog.api.dto;

import com.Blog.api.model.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
    private String authorUsername;
    private String content;


    public PostDto(String authorUsername, String content) {
        this.authorUsername = authorUsername;
        this.content = content;
    }
}
