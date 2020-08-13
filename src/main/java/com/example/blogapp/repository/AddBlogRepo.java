package com.example.blogapp.repository;

import com.example.blogapp.entity.addBlog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddBlogRepo extends JpaRepository<addBlog, Integer> {
}
