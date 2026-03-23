package com.Blog.api.dto;

public class UserDto {
    private String username;

    public UserDto(String username) {
        this.username = username;    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getUsername() {
        return username;
    }

}
