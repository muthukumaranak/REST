package com.example.blogapp.repository;

import com.example.blogapp.entity.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface BlogPostRepo extends JpaRepository<BlogPost, Integer> {

    @Query(value = "select authorname, count(authorname) as count FROM blogapp.blogs group by authorname order by authorname asc",nativeQuery = true)
    List<Object[]> getCount();

}
