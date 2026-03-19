package com.Blog.api.repo;

import com.Blog.api.model.Like;
import com.Blog.api.model.Post;
import com.Blog.api.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface LikeRepo extends JpaRepository<Like, Long> {
    Integer countByPost(int id);
    boolean existByPostAndUser(Post post, Users user);
    void deleteByPostAndUser(Post post, Users user);
    Integer countByPost(Post post);
    Optional<Like> findByPostAndUser(Post post, Users user);
}