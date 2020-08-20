package com.example.blogapp.repository;

import com.example.blogapp.entity.Tags;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepo extends JpaRepository<Tags,Integer> {

}
