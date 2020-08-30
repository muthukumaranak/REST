package com.example.blogapp.service;


import com.example.blogapp.entity.BlogPost;
import com.example.blogapp.entity.Comment;
import com.example.blogapp.repository.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    CommentRepo commentRepo;

    public String create(String id, String email, String comment) {
        int blogid = Integer.parseInt(id);
        try {
            BlogPost blogPost = new BlogPost();
            blogPost.setId(blogid);
            Comment commentObj = new Comment(blogPost,comment,email);
            commentRepo.save(commentObj);
            return "Comment Added";
        }
        catch (Exception e){
            return "Could not add Comment";
        }
    }

    public List<CommentDTO> retrieve() {
        List<CommentDTO> commentDTOS = new ArrayList<>();
        List<Comment> commentList = commentRepo.findAll();
        commentList.forEach(comments -> {
            CommentDTO commentDTO = new CommentDTO();
            commentDTO.setComment(comments.getComment());
            commentDTO.setCommentby(comments.getCommentby());
            commentDTO.setId(comments.getId());
            commentDTOS.add(commentDTO);
        });
        return commentDTOS;
    }

    public String update(String id, String comment) {
        try{
            int commentId = Integer.parseInt(id);
            commentRepo.update(commentId,comment);
            return "Comment Updated";
        }
        catch (Exception e){
            return "Not Updated";
        }
    }

    public String delete(String id) {
        try{
            int commentId = Integer.parseInt(id);
            commentRepo.delete(commentId);
            return "Deleted";
        }
        catch (Exception e){
            return "Cannot be Deleted";
        }
    }

    public String createcomment(String id, String email, String comment) {
        int blogid = Integer.parseInt(id);
        try {
            BlogPost blogPost = new BlogPost();
            blogPost.setId(blogid);
            Comment commentObj = new Comment(blogPost,comment,email);
            commentRepo.save(commentObj);
            return "Comment Added";
        }
        catch (Exception e){
            return "Could not add Comment";
        }
    }

    public String updatecomment(String id, String email, String comment) {
        try{
            int commentId = Integer.parseInt(id);
            if(email.equals(commentRepo.findMail(commentId)))
                commentRepo.update(commentId,comment);
            else
                return "Not Updated";
            return "Comment Updated";
        }
        catch (Exception e){
            return "Not Updated";
        }
    }

    public String deletecomment(String id, String email) {
        try{
            int commentId = Integer.parseInt(id);
            if(email.equals(commentRepo.findMail(commentId)))
                commentRepo.delete(commentId);
            else
                return "Cannot be Deleted";
            return "Deleted";
        }
        catch (Exception e){
            return "Cannot be Deleted";
        }
    }
}
