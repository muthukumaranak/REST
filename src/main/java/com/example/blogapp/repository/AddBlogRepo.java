package com.example.blogapp.repository;

import com.example.blogapp.entity.addBlog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AddBlogRepo extends JpaRepository<addBlog, Integer> {

}
