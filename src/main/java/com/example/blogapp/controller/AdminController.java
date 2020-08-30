package com.example.blogapp.controller;

import com.example.blogapp.entity.Users;
import com.example.blogapp.repository.BlogPostRepo;
import com.example.blogapp.repository.CommentRepo;
import com.example.blogapp.repository.UsersRepo;
import com.example.blogapp.service.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    BlogPostRepo blogPostRepo;

    @Autowired
    CommentRepo commentRepo;

    @Autowired
    UsersRepo usersRepo;

    @GetMapping("/users")
    public List<UserDTO> users(){
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

    @DeleteMapping("/deletepost")
    public String deletepost(@RequestParam String id){
        try{
            int blogId = Integer.parseInt(id);
            blogPostRepo.deleteById(blogId);
            return "Deleted";
        }
        catch (Exception e){
            return "Cannot Delete";
        }
    }

    @DeleteMapping("/deletecomment")
    public String deletecomment(@RequestParam String id){
        try{
            int commentId = Integer.parseInt(id);
            commentRepo.delete(commentId);
            return "Deleted";
        }
        catch (Exception e){
            return "Cannot be Deleted";
        }
    }


}
