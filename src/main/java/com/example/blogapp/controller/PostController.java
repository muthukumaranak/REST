package com.example.blogapp.controller;

import com.example.blogapp.service.BlogPostDTO;
import com.example.blogapp.service.BlogServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blog")
@Api(value="Blog Post")
public class PostController {

    @Autowired
    BlogServiceImpl blogService;

    //Creating blog post
    @ApiOperation(value = "Creating a Blog as Guest", response = String.class)
    @ApiParam(value = "Name, Email, Title, Blog Content, Excerpt", required = true)
    @PostMapping
    public String create(@RequestParam String name, @RequestParam String email, @RequestParam String title, @RequestParam String content, @RequestParam String excerpt){
        return blogService.create(name, email, title, content, excerpt);
    }

    //Retrieve all blog post

    @ApiOperation(value = "Getting all blogs", response = List.class)
    @ApiParam(value = "No param's Required", required = false)
    @GetMapping
    public List<BlogPostDTO> retrieve() {
        return blogService.retrieve();
    }

    //Updating Blog
    @ApiOperation(value = "Updating a Blog", response = String.class)
    @ApiParam(value = "Id, Content", required = true)
    @PutMapping
    public String update(@RequestParam String id, @RequestParam String content){
        return blogService.update(id, content);
    }

    //deleting the blog
    @ApiOperation(value = "Deleting a Blog", response = String.class)
    @ApiParam(value = "Id", required = true)
    @DeleteMapping
    public String delete(@RequestParam String id){
        return blogService.delete(id);
    }

    @ApiOperation(value = "Getting my blogs", response = List.class)
    @ApiParam(value = "Email", required = true)
    @GetMapping("/myblog")
    public List<BlogPostDTO> myblog(@RequestParam String email){
        return blogService.myblog(email);
    }
}
