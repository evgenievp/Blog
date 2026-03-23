package com.Blog.api.repo;

import com.Blog.api.dto.UserDto;
import com.Blog.api.model.Post;
import com.Blog.api.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Integer> {
    @Query("SELECT l.user FROM Like l WHERE l.post.id = :postId")
    List<Users> findUsersByPostId(int postId);
}
