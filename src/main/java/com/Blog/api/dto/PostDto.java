package com.Blog.api.dto;

import com.Blog.api.model.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
    private Users author;
    private int likes = 0;

    public PostDto(Users author, int likes) {
        this.author = author;
        this.likes = likes;
    }

    public PostDto(Users author) {
        this.author = author;
        this.likes = 0;
    }
}
