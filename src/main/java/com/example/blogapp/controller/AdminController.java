package com.example.blogapp.controller;

import com.example.blogapp.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
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

    @Autowired
    JwtUtil jwtUtil;

    public String username = null;

    @ApiOperation(value = "View a list of available users", response = List.class)
    @ApiParam(value = "UserName, Password", required = true)
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    @GetMapping("/users")
    public List<UserDTO> users(HttpServletRequest request) {
        username = (String) request.getAttribute("userid");
        return userDetailsService.getAllUsers();
    }


    @ApiOperation(value = "Deleting Any Post", response = String.class)
    @ApiParam(value = "Blog ID is Required", required = true)
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    @DeleteMapping("/deletepost")
    public String deletepost(@RequestParam String id) {
        return blogService.delete(id);
    }

    @ApiOperation(value = "Deleting Any Comment", response = String.class)
    @ApiParam(value = "Comment ID is Required", required = true)
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    @DeleteMapping("/deletecomment")
    public String deletecomment(@RequestParam String id) {
        return commentService.delete(id);
    }


    @GetMapping("/username")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    public String username(@RequestParam String token){
        return jwtUtil.extractUsername(token);
    }
}
