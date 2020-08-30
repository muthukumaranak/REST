package com.example.blogapp.controller;

import com.example.blogapp.service.BlogPostDTO;
import com.example.blogapp.service.BlogServiceImpl;
import com.example.blogapp.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/guest")
@Api(value="Guest Page")
public class GuestController {

    @Autowired
    BlogServiceImpl blogService;

    @Autowired
    CommentService commentService;

    //creating blog
    @ApiOperation(value = "Creating a Blog as Guest", response = String.class)
    @ApiParam(value = "Name, Email, Title, Blog Content, Excerpt", required = true)
    @PostMapping("/blog")
    public String create(@RequestParam String name, @RequestParam String email, @RequestParam String title, @RequestParam String content, @RequestParam String excerpt){
        return blogService.create(name, email, title, content, excerpt);
    }

    //Retrieve all blog post
    @ApiOperation(value = "Getting all Posts", response = List.class)
    @ApiParam(value = "No Param's Required", required = false)
    @GetMapping("/blog")
    public List<BlogPostDTO> retrieve() {
        return blogService.retrieve();
    }

    //Updating Blog
    @ApiOperation(value = "Updating a Blog", response = String.class)
    @ApiParam(value = "Id, Email, Content", required = true)
    @PutMapping("/blog")
    public String update(@RequestParam String id, @RequestParam String email, @RequestParam String content){
        return blogService.guestupdate(id, email, content);
    }

    //deleting the blog
    @ApiOperation(value = "Deleting a Blog", response = String.class)
    @ApiParam(value = "Id, Email", required = true)
    @DeleteMapping("/blog")
    public String delete(@RequestParam String id, @RequestParam String email){
        return blogService.guestdelete(id,email);
    }

    //Creating comment
    @ApiOperation(value = "Creating a Comment", response = String.class)
    @ApiParam(value = "Id, Email, Comment", required = true)
    @PostMapping("/comment")
    public String createcomment(@RequestParam String id, @RequestParam String email, @RequestParam String comment){
        return commentService.createcomment(id, email,comment);
    }

    @ApiOperation(value = "Updating a Comment", response = String.class)
    @ApiParam(value = "Id, Email, Comment", required = true)
    @PutMapping("/comment")
    public String updatecomment(@RequestParam String id, @RequestParam String email, @RequestParam String comment){
        return commentService.updatecomment(id,email,comment);
    }

    @ApiOperation(value = "Deleting a Comment", response = String.class)
    @ApiParam(value = "Id, Email", required = true)
    @DeleteMapping("/comment")
    public String deletecomment(@RequestParam String id, @RequestParam String email){
        return commentService.deletecomment(id,email);
    }


}
