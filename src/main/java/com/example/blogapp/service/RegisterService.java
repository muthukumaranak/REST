package com.example.blogapp.service;


import com.example.blogapp.entity.Registration;
import com.example.blogapp.repository.RegisterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RegisterService {

    @Autowired
    RegisterRepo registerRepo;

    public String add(String name,  String email,  String password){
        try {
        Registration registration = new Registration(name,email,password);
        registerRepo.save(registration);}
        catch (Exception e){
            System.out.println(e);
            return "negative";
        }
        return "positive";
    }
}
