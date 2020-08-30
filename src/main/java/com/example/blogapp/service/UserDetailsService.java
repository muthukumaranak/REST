package com.example.blogapp.service;

import com.example.blogapp.entity.Users;
import com.example.blogapp.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    public UsersRepo usersRepo;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = usersRepo.findByName(username);
        return new User(user.getName(), user.getPassword(), mapRolesToAuthorities(user));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Users users) {
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority(users.getRole()));
        return list;
    }

    public String addUser(String name, String email, String password) {
        try {
            Users users = new Users(name, email, bCryptPasswordEncoder.encode(password));
            users.setRole("user");
            usersRepo.save(users);
        } catch (Exception e) {
            return "User Cannot Be Registered";
        }
        return "Registration Success";
    }

    public List<UserDTO> getAllUsers() {
        List<UserDTO> list = new ArrayList<>();
        List<Users> users = usersRepo.findAll();
        users.forEach(user -> {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setName(user.getName());
            list.add(userDTO);
        });
        return list;
    }
}