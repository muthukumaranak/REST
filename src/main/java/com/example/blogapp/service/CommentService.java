package com.example.blogapp.service;


import com.example.blogapp.entity.BlogPost;
import com.example.blogapp.entity.Comment;
import com.example.blogapp.repository.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    CommentRepo commentRepo;

    public void addComment(int blogid, String comment, String commentby){
        try {
           // Comment commentObj = new Comment(blogid,comment,commentby);
            //commentRepo.save(commentObj);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public List<Comment> getAll() {
        List<Comment> list = (List<Comment>)commentRepo.findAll();
        return list;
    }
}
