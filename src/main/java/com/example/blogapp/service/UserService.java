package com.example.blogapp.service;


import com.example.blogapp.entity.Users;
import com.example.blogapp.repository.BlogPostRepo;
import com.example.blogapp.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserService implements UserServiceInterface {

    String session_user_name;

    private UsersRepo usersRepo;

    @Autowired
    BlogPostRepo blogPostRepo;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UsersRepo usersRepo) {
        super();
        this.usersRepo = usersRepo;
    }

    @Override
    public Users save(UserRegistrationDto registrationDto) {
        Users users = new Users(registrationDto.getName(),registrationDto.getEmail(),
                bCryptPasswordEncoder.encode(registrationDto.getPassword()), registrationDto.getRoles());
        return usersRepo.save(users);
    }

    public List<Object[]> getall() {
        List<Object[]> list = blogPostRepo.getCount();
        return list;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users users = usersRepo.findByEmail(email);
        session_user_name = email;
        if(users == null){
            throw new UsernameNotFoundException("Invalid username or password");
        }
        return new User(users.getEmail(),users.getPassword(),mapRolesToAuthorities(users));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Users users){
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority(users.getRole()));
        return list;
    }

    public String gettingName(){
        return usersRepo.getName(session_user_name);
    }

    public String add(String name,  String email,  String password){
        try {
            Users users = new Users(name,email,bCryptPasswordEncoder.encode(password));
            users.setRole("user");
            usersRepo.save(users);}
        catch (Exception e){
            System.out.println(e);
            return "negative";
        }
        return "positive";
    }

}
