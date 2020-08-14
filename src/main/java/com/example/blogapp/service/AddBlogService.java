package com.example.blogapp.service;

import com.example.blogapp.entity.BlogPost;
import com.example.blogapp.repository.BlogPostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddBlogService {

    @Autowired
    BlogPostRepo blogPostRepo;

    public void addBlog(String name, String email, String title, String blogcontent){
        try {
            BlogPost ab = new BlogPost(name, email, title, blogcontent);
            blogPostRepo.save(ab);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public List<BlogPost> getall() {
        List<BlogPost> list = (List<BlogPost>) blogPostRepo.findAll();
        return list;
    }

    public void addLike(int bid, int likes) {
        blogPostRepo.postLike(likes, bid);
    }
}
