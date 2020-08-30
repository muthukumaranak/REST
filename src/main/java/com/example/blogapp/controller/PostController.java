package com.example.blogapp.controller;

import com.example.blogapp.service.BlogPostDTO;
import com.example.blogapp.service.BlogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blog")
public class PostController {

    @Autowired
    BlogServiceImpl blogService;

    //Creating blog post
    @PostMapping
    public String create(@RequestParam String name, @RequestParam String email, @RequestParam String title, @RequestParam String content, @RequestParam String excerpt){
        return blogService.create(name, email, title, content, excerpt);
    }

    //Retrieve all blog post
    @GetMapping
    public List<BlogPostDTO> retrieve() {
        return blogService.retrieve();
    }

    //Updating Blog
    @PutMapping
    public String update(@RequestParam String id, @RequestParam String content){
        return blogService.update(id, content);
    }

    //deleting the blog
    @DeleteMapping
    public String delete(@RequestParam String id){
        return blogService.delete(id);
    }

    @GetMapping("/myblog")
    public List<BlogPostDTO> myblog(@RequestParam String email){
        return blogService.myblog(email);
    }
}
