package com.example.blogapp.controller;

import com.example.blogapp.entity.BlogPost;
import com.example.blogapp.service.BlogPostDTO;
import com.example.blogapp.service.BlogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    BlogServiceImpl blogService;

    @GetMapping("/find")
    public List<BlogPostDTO> findblog(@RequestParam String id){
        return blogService.find(id);
    }

    @GetMapping("/search")
    public List<BlogPost> search(@RequestParam String keyword){
        return blogService.search(keyword);
    }

    @GetMapping("/filter")
    public List<BlogPostDTO> filter(@RequestParam String name, @RequestParam String content, @RequestParam String excerpt){
        return blogService.filter(name,content,excerpt);
    }

    @GetMapping("/filterAndSearch")
    public List<BlogPost> filterAndSearch(@RequestParam String keyword, @RequestParam String name, @RequestParam String content, @RequestParam String excerpt){
        return blogService.filterAndSearch(keyword,name,content,excerpt);
    }


    @GetMapping("/page")
    public List<BlogPostDTO> Paginated(@RequestParam int page, @RequestParam String sort, @RequestParam String direction, @RequestParam  String limit) {
        return blogService.paginated(page,sort,direction,limit);
    }


}
