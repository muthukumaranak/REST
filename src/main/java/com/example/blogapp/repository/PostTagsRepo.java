package com.example.blogapp.repository;

import com.example.blogapp.entity.PostTags;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostTagsRepo extends JpaRepository<PostTags, Integer> {
}
