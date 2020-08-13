package com.example.blogapp.service;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.example.blogapp.entity.Registration;
import com.example.blogapp.repository.RegisterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {

    @Autowired
    RegisterRepo registerRepo ;

    public String logincheck(String name, String password){
        String originalPassword = registerRepo.validateemail(name);
        if(password.equals(originalPassword))
            return "positive";
        return "negative";
    }

    public String getName(String username) {
        String name = registerRepo.getName(username);
        return name;
    }
}
