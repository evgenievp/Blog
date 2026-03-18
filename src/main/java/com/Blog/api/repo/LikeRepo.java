package com.Blog.api.repo;

import com.Blog.api.model.Like;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Table(
    uniqueConstraints = @UniqueConstraint(columnNames = {"post_id", "user_id"})
)

@Repository
public interface LikeRepo extends JpaRepository<Like, Long> {
    Integer countByPost(int id);

}