package com.example.blogapp.controller;

import com.example.blogapp.service.BlogServiceImpl;
import com.example.blogapp.service.CommentService;
import com.example.blogapp.service.UserDTO;
import com.example.blogapp.service.UserDetailsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
@RequestMapping("/admin")
@Api(value="Admin Controller")
public class AdminController {

    @Autowired
    BlogServiceImpl blogService;

    @Autowired
    CommentService commentService;

    @Autowired
    UserDetailsService userDetailsService;

    @ApiOperation(value = "View a list of available users", response = List.class)
    @ApiParam(value = "UserName, Password", required = true)
    @GetMapping("/users")
    public List<UserDTO> users(HttpServletRequest request) {
        //System.out.println(request.getAttribute("userid"));
        return userDetailsService.getAllUsers();
    }

    @ApiOperation(value = "Deleting Any Post", response = String.class)
    @ApiParam(value = "Blog ID is Required", required = true)
    @DeleteMapping("/deletepost")
    public String deletepost(@RequestParam String id) {
        return blogService.delete(id);
    }

    @ApiOperation(value = "Deleting Any Comment", response = String.class)
    @ApiParam(value = "Comment ID is Required", required = true)
    @DeleteMapping("/deletecomment")
    public String deletecomment(@RequestParam String id) {
        return commentService.delete(id);
    }

}
