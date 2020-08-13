package com.example.blogapp.service;


import com.example.blogapp.entity.Registration;
import com.example.blogapp.repository.RegistrationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RegisterService {

    @Autowired
    RegistrationRepo registrationRepo;

    public String add(String name,  String email,  String password){
        try {
        Registration registration = new Registration(name,email,password);
        registrationRepo.save(registration);}
        catch (Exception e){
            System.out.println(e);
            return "negative";
        }
        return "positive";
    }

    public List<Registration> getall() {
        List<Registration> list = registrationRepo.findAll();
        return list;
    }
}
