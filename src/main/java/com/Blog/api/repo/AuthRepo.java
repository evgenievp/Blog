package com.Blog.api.repo;


import com.Blog.api.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepo extends JpaRepository<Users, Integer> {
}
