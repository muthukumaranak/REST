package com.example.blogapp.service;

import com.example.blogapp.repository.RegistrationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    RegistrationRepo registrationRepo;

    public String adminlogincheck(String name, String password){
        String originalPassword = registrationRepo.validateemail(name);
        String role = registrationRepo.getRole(name);
        if(password.equals(originalPassword) && role.equals("admin"))
            return "positive";
        return "negative";
    }

    public String logincheck(String name, String password){
        String originalPassword = registrationRepo.validateemail(name);
        String role = registrationRepo.getRole(name);
        if(password.equals(originalPassword) && role.equals("user"))
            return "positive";
        return "negative";
    }

    public String getName(String username) {
        String name = registrationRepo.getName(username);
        return name;
    }
}
