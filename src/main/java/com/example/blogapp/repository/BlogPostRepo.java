package com.example.blogapp.repository;

import com.example.blogapp.entity.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogPostRepo extends JpaRepository<BlogPost, Integer> {

}
