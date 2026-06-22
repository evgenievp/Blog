package com.Blog.api.repo;


import com.Blog.api.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepo extends JpaRepository<Users, Integer> {
    Optional<Users> findByUsername(String username);
}
