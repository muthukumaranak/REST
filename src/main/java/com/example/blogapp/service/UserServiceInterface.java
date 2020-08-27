package com.example.blogapp.service;

import com.example.blogapp.entity.Users;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserServiceInterface extends UserDetailsService {
    Users save(UserRegistrationDto registrationDto);
}
