package com.example.blogapp.controller;

import com.example.blogapp.repository.UsersRepo;
import com.example.blogapp.service.BlogPostDTO;
import com.example.blogapp.service.BlogServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/blog")
@Api(value = "Blog Post")
public class PostController {

    @Autowired
    BlogServiceImpl blogService;

    @Autowired
    UsersRepo usersRepo;

    public String username = null;
    public String useremail = null;

    //Creating blog post
    @ApiOperation(value = "Creating a Blog as Guest", response = String.class)
    @ApiParam(value = "Title, Blog Content, Excerpt", required = true)
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    @PostMapping
    public String create(HttpServletRequest request, @RequestParam String title,
                         @RequestParam String content, @RequestParam String excerpt) {
        username = usersRepo.getName((String) request.getAttribute("userid"));
        useremail = (String) request.getAttribute("userid");
        return blogService.create(username, useremail, title, content, excerpt);
    }

    //Retrieve all blog post

    @ApiOperation(value = "Getting all blogs", response = List.class)
    @ApiParam(value = "No param's Required", required = false)
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    @GetMapping
    public List<BlogPostDTO> retrieve() {
        return blogService.retrieve();
    }

    //Updating Blog
    @ApiOperation(value = "Updating a Blog", response = String.class)
    @ApiParam(value = "Id, Content", required = true)
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    @PutMapping
    public String update(@RequestParam String id, @RequestParam String content) {
        return blogService.update(id, content);
    }

    //deleting the blog
    @ApiOperation(value = "Deleting a Blog", response = String.class)
    @ApiParam(value = "Id", required = true)
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    @DeleteMapping
    public String delete(@RequestParam String id) {
        return blogService.delete(id);
    }

    @ApiOperation(value = "Getting my blogs", response = List.class)
    @ApiParam(value = "No Param's", required = false)
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    @GetMapping("/myblog")
    public List<BlogPostDTO> myblog(HttpServletRequest request) {
        return blogService.myblog((String) request.getAttribute("userid"));
    }
}
