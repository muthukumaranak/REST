package com.example.blogapp.service;

import com.example.blogapp.entity.Registration;
import com.example.blogapp.entity.addBlog;
import com.example.blogapp.repository.AddBlogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class addBlogService {

    @Autowired
    AddBlogRepo addBlogRepo;

    public void addBlog(String email, String title, String blogcontent){
        try {
            addBlog ab = new addBlog(email, title,blogcontent);
            addBlogRepo.save(ab);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public List<addBlog> getall() {
        List<addBlog> list = (List<addBlog>)addBlogRepo.findAll();
        return list;
    }
}
