package com.example.blogapp.controller;

import com.example.blogapp.entity.BlogPost;
import com.example.blogapp.model.JwtRequest;
import com.example.blogapp.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/home")
@Api(value="Home Page")
public class HomeController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    BlogServiceImpl blogService;

    @Autowired
    UserDetailsService userDetailsService;

    @ApiOperation(value = "Finding a Blog", response = List.class)
    @ApiParam(value = "Id", required = true)
    @GetMapping("/find")
    public List<BlogPostDTO> findblog(@RequestParam String id) {
        return blogService.find(id);
    }


    @ApiOperation(value = "Searching a Blog", response = List.class)
    @ApiParam(value = "Keyword", required = true)
    @GetMapping("/search")
    public List<BlogPost> search(@RequestParam String keyword) {
        return blogService.search(keyword);
    }

    @ApiOperation(value = "Filtering", response = List.class)
    @ApiParam(value = "Name, Content, Excerpt", required = true)
    @GetMapping("/filter")
    public List<BlogPostDTO> filter(@RequestParam String name, @RequestParam String content, @RequestParam String excerpt) {
        return blogService.filter(name, content, excerpt);
    }

    @ApiOperation(value = "Filtering and Searching", response = List.class)
    @ApiParam(value = "Keyword, Authorname, Content, Excerpt", required = true)
    @GetMapping("/filterAndSearch")
    public List<BlogPost> filterAndSearch(@RequestParam String keyword, @RequestParam String name, @RequestParam String content, @RequestParam String excerpt) {
        return blogService.filterAndSearch(keyword, name, content, excerpt);
    }


    @ApiOperation(value = "Pagination", response = List.class)
    @ApiParam(value = "Page no, Sort by, Sort Direction(asc,des), limit for Page", required = true)
    @GetMapping("/page")
    public List<BlogPostDTO> Paginated(@RequestParam int page, @RequestParam String sort, @RequestParam String direction, @RequestParam String limit) {
        return blogService.paginated(page, sort, direction, limit);
    }

    @ApiOperation(value = "Generating a Token", response = String.class)
    @ApiParam(value = "userName, password", required = true)
    @PostMapping("/token")
    public String generateToken(@RequestBody JwtRequest request) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));
        } catch (Exception ex) {
            throw new Exception("Invalid Credentials.");
        }
        return jwtUtil.generateToken(request.getUserName());
    }

    @ApiOperation(value = "Registering for a new user", response = String.class)
    @ApiParam(value = "Name, Email, Password", required = true)
    @PostMapping("/register")
    public String addUser(@RequestParam String name, @RequestParam String email, @RequestParam String password){
        return userDetailsService.addUser(name,email,password);
    }

}
