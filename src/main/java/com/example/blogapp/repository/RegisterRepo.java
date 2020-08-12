package com.example.blogapp.repository;

import com.example.blogapp.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisterRepo extends JpaRepository<Registration, Integer> {
}
