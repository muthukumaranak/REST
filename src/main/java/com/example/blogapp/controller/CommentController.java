package com.example.blogapp.controller;

import com.example.blogapp.service.CommentDTO;
import com.example.blogapp.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping
    public String create(@RequestParam String id, @RequestParam String email, @RequestParam String comment){
        return commentService.create(id,email,comment);
    }

    @GetMapping
    public List<CommentDTO> retrieve(){
       return commentService.retrieve();
    }

    @PutMapping
    public String update(@RequestParam String id, @RequestParam String comment){
        return commentService.update(id,comment);
    }

    @DeleteMapping
    public String delete(@RequestParam String id){
        return commentService.delete(id);
    }

}
