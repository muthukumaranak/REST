package com.example.blogapp.controller;

import com.example.blogapp.service.BlogServiceImpl;
import com.example.blogapp.service.CommentService;
import com.example.blogapp.service.UserDTO;
import com.example.blogapp.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    BlogServiceImpl blogService;

    @Autowired
    CommentService commentService;

    @Autowired
    UserDetailsService userDetailsService;

    @GetMapping("/users")
    public List<UserDTO> users(HttpServletRequest request) {
        //System.out.println(request.getAttribute("userid"));
        return userDetailsService.getAllUsers();
    }

    @DeleteMapping("/deletepost")
    public String deletepost(@RequestParam String id) {
        return blogService.delete(id);
    }

    @DeleteMapping("/deletecomment")
    public String deletecomment(@RequestParam String id) {
        return commentService.delete(id);
    }

}
