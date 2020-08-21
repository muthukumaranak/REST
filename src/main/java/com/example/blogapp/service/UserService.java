package com.example.blogapp.service;


import com.example.blogapp.entity.Users;
import com.example.blogapp.repository.BlogPostRepo;
import com.example.blogapp.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    @Autowired
    UsersRepo usersRepo;

    @Autowired
    BlogPostRepo blogPostRepo;

    public String add(String name,  String email,  String password){
        try {
        Users users = new Users(name,email,password);
        usersRepo.save(users);}
        catch (Exception e){
            System.out.println(e);
            return "negative";
        }
        return "positive";
    }

    public List<Object[]> getall() {
        List<Object[]> list = blogPostRepo.getCount();
        return list;
    }
}
