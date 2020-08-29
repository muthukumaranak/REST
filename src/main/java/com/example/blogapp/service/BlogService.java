package com.example.blogapp.service;

import com.example.blogapp.entity.BlogPost;
import com.example.blogapp.repository.BlogPostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class BlogService {

    @Autowired
    BlogPostRepo blogPostRepo;

    public int addBlog(String name, String email, String title, String blogcontent, String excerpt){
        int id = 0;
        try {
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = f.format(new Date());
            BlogPost blogPost = new BlogPost(name, email, title, blogcontent, 0, time, excerpt);
            blogPostRepo.save(blogPost);
            id = blogPost.getId();
        }
        catch (Exception e){
            System.out.println(e);
        }
        return id;
    }

    public List<BlogPost> getall() {
        List<BlogPost> list = (List<BlogPost>) blogPostRepo.findAll();
        return list;
    }
    
    public void removeBlog(int id){
        blogPostRepo.deleteById(id);
    }

    public void updateblog(int blogid, String blogcontent){
        blogPostRepo.updateblogs(blogcontent,blogid);
    }


    public List<BlogPost> search(String searchvalue) {
        List<BlogPost> list = (List<BlogPost>)blogPostRepo.search(searchvalue);
        return list;
    }

}
