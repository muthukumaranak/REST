package com.example.blogapp.controller;

import com.example.blogapp.entity.BlogPost;
import com.example.blogapp.entity.Users;
import com.example.blogapp.repository.BlogPostRepo;
import com.example.blogapp.repository.CommentRepo;
import com.example.blogapp.repository.UsersRepo;
import com.example.blogapp.service.BlogPostDTO;
import com.example.blogapp.service.CommentDTO;
import com.example.blogapp.service.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class RestControl {

    @Autowired
    BlogPostRepo blogPostRepo;

    @Autowired
    CommentRepo commentRepo;

    @Autowired
    UsersRepo usersRepo;

    @GetMapping("/blogs")
    public List<BlogPostDTO> get() {
        List<BlogPostDTO> blogPostDTOS = new LinkedList<>();
        List<BlogPost> list = blogPostRepo.findAll();
        list.forEach(blogPost -> {
            BlogPostDTO blogPostDTO = new BlogPostDTO();
            blogPostDTO.setId(blogPost.getId());
            blogPostDTO.setAuthorname(blogPost.getAuthorname());
            blogPostDTO.setBlogcontent(blogPost.getBlogcontent());
            blogPostDTO.setEmail(blogPost.getEmail());
            blogPostDTO.setTitle(blogPost.getTitle());
            blogPostDTO.setExcerpt(blogPost.getExcerpt());
            blogPostDTO.setTime(blogPost.getTime());

            List<CommentDTO> commentDTOS = new ArrayList<>();

            blogPost.getCommentList().forEach(comment -> {
                CommentDTO commentDTO = new CommentDTO();
                commentDTO.setCommentby(comment.getCommentby());
                commentDTO.setComment(comment.getComment());
                commentDTO.setId(comment.getId());
                commentDTOS.add(commentDTO);
            });
            blogPostDTO.setComments(commentDTOS);
            blogPostDTOS.add(blogPostDTO);
        });
        return blogPostDTOS;
    }


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
    
    @PostMapping("/createblog")
    public String blogpost(@RequestParam String authorname, @RequestParam String email,@RequestParam String title,@RequestParam String blogcontent,@RequestParam String excerpt){
        try{
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = f.format(new Date());
            BlogPost blogPost = new BlogPost(authorname, email, title, blogcontent, 0, time, excerpt);
            blogPostRepo.save(blogPost);
            return "Inserted";
        }
        catch (Exception e){
            return "Unable to Insert";
        }
    }

    @GetMapping("/findblog")
    public List<BlogPostDTO> findblog(@RequestParam String id){
        int blogId = Integer.parseInt(id);
        List<BlogPostDTO> blogPostDTOS = new LinkedList<>();
        List<BlogPost> list = blogPostRepo.findAllById(blogId);
        list.forEach(blogPost -> {
            BlogPostDTO blogPostDTO = new BlogPostDTO();
            blogPostDTO.setId(blogPost.getId());
            blogPostDTO.setAuthorname(blogPost.getAuthorname());
            blogPostDTO.setBlogcontent(blogPost.getBlogcontent());
            blogPostDTO.setEmail(blogPost.getEmail());
            blogPostDTO.setTitle(blogPost.getTitle());
            blogPostDTO.setExcerpt(blogPost.getExcerpt());
            blogPostDTO.setTime(blogPost.getTime());
            List<CommentDTO> commentDTOS = new ArrayList<>();
            blogPost.getCommentList().forEach(comment -> {
                CommentDTO commentDTO = new CommentDTO();
                commentDTO.setCommentby(comment.getCommentby());
                commentDTO.setComment(comment.getComment());
                commentDTO.setId(comment.getId());
                commentDTOS.add(commentDTO);
            });
            blogPostDTO.setComments(commentDTOS);
            blogPostDTOS.add(blogPostDTO);
        });
        return blogPostDTOS;
    }

    @PutMapping("/updateblog")
    public String blogUpdate(@RequestParam String id, @RequestParam String content){
        try{
            int blogId = Integer.parseInt(id);
            blogPostRepo.updateblogs(content,blogId);
            return "updated";
        }
        catch (Exception e){
            return "Cannot Update";
        }
    }

    @DeleteMapping("/deleteblog")
    public String blogdelete(@RequestParam String id){
        try{
            int blogId = Integer.parseInt(id);
            blogPostRepo.deleteById(blogId);
            return "Deleted";
        }
        catch (Exception e){
            return "Cannot Delete";
        }
    }

    @GetMapping("/search")
    public List<BlogPost> search(@RequestParam String keyword){
        List<BlogPost> list = blogPostRepo.search(keyword);
        return list;
    }

    @GetMapping("/filter")
    public List<BlogPostDTO> filter(@RequestParam String name, @RequestParam String content, @RequestParam String excerpt){
        List<BlogPostDTO> blogPostDTOS = new LinkedList<>();
        List<BlogPost> list = blogPostRepo.filter(name, content, excerpt);
        list.forEach(blogPost -> {
            BlogPostDTO blogPostDTO = new BlogPostDTO();
            blogPostDTO.setId(blogPost.getId());
            blogPostDTO.setAuthorname(blogPost.getAuthorname());
            blogPostDTO.setBlogcontent(blogPost.getBlogcontent());
            blogPostDTO.setEmail(blogPost.getEmail());
            blogPostDTO.setTitle(blogPost.getTitle());
            blogPostDTO.setExcerpt(blogPost.getExcerpt());
            blogPostDTO.setTime(blogPost.getTime());
            List<CommentDTO> commentDTOS = new ArrayList<>();
            blogPost.getCommentList().forEach(comment -> {
                CommentDTO commentDTO = new CommentDTO();
                commentDTO.setCommentby(comment.getCommentby());
                commentDTO.setComment(comment.getComment());
                commentDTO.setId(comment.getId());
                commentDTOS.add(commentDTO);
            });
            blogPostDTO.setComments(commentDTOS);
            blogPostDTOS.add(blogPostDTO);
        });
        return blogPostDTOS;
    }
}
