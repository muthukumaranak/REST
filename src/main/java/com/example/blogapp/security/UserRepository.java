package com.example.blogapp.security;

import com.example.blogapp.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users,Integer> {
    Users findByUsername(String email);
}
