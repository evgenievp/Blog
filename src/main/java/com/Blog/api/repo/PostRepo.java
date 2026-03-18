package com.Blog.api.repo;

import com.Blog.api.dto.UserDto;
import com.Blog.api.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Integer> {

    @Query(nativeQuery = true, value = """
            select count(distinct userId) from post where post.id = postId;
            """)
    List<UserDto> findAllById(int postId);
}
