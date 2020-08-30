package com.example.blogapp.controller;

import com.example.blogapp.service.BlogPostDTO;
import com.example.blogapp.service.BlogServiceImpl;
import com.example.blogapp.service.CommentDTO;
import com.example.blogapp.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/guest")
public class GuestController {

    @Autowired
    BlogServiceImpl blogService;

    @Autowired
    CommentService commentService;

    //creating blog
    @PostMapping("/blog")
    public String create(@RequestParam String name, @RequestParam String email, @RequestParam String title, @RequestParam String content, @RequestParam String excerpt){
        return blogService.create(name, email, title, content, excerpt);
    }

    //Retrieve all blog post
    @GetMapping("/blog")
    public List<BlogPostDTO> retrieve() {
        return blogService.retrieve();
    }

    //Updating Blog
    @PutMapping("/blog")
    public String update(@RequestParam String id, @RequestParam String email, @RequestParam String content){
        return blogService.guestupdate(id, email, content);
    }

    //deleting the blog
    @DeleteMapping("/blog")
    public String delete(@RequestParam String id, @RequestParam String email){
        return blogService.guestdelete(id,email);
    }

    //Creating comment
    @PostMapping("/comment")
    public String createcomment(@RequestParam String id, @RequestParam String email, @RequestParam String comment){
        return commentService.createcomment(id, email,comment);
    }

    @PutMapping("/comment")
    public String updatecomment(@RequestParam String id, @RequestParam String email, @RequestParam String comment){
        return commentService.updatecomment(id,email,comment);
    }

    @DeleteMapping("/comment")
    public String deletecomment(@RequestParam String id, @RequestParam String email){
        return commentService.deletecomment(id,email);
    }


}
