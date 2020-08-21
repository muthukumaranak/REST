package com.example.blogapp.service;

import com.example.blogapp.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    UsersRepo usersRepo;

    public String adminlogincheck(String name, String password){
        String originalPassword = usersRepo.validateemail(name);
        String role = usersRepo.getRole(name);
        if(password.equals(originalPassword) && role.equals("admin"))
            return "positive";
        return "negative";
    }

    public String logincheck(String name, String password){
        String originalPassword = usersRepo.validateemail(name);
        String role = usersRepo.getRole(name);
        if(password.equals(originalPassword) && role.equals("user"))
            return "positive";
        return "negative";
    }

    public String getName(String username) {
        String name = usersRepo.getName(username);
        return name;
    }
}
