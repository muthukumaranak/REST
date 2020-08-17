package com.example.blogapp.service;

import com.example.blogapp.entity.BlogPost;
import com.example.blogapp.repository.BlogPostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class AddBlogService {

    @Autowired
    BlogPostRepo blogPostRepo;

    public void addBlog(String name, String email, String title, String blogcontent){
        try {
            SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            String time = f.format(new Date());
            BlogPost ab = new BlogPost(name, email, title, blogcontent, 0, time);
            blogPostRepo.save(ab);
        }
        catch (Exception e){
            System.out.println(e);
        }
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

    public void addLike(int bid, int likes) {
        blogPostRepo.postLike(likes, bid);
    }

    public List<BlogPost> search(String searchvalue) {
        List<BlogPost> list = (List<BlogPost>)blogPostRepo.search(searchvalue);
        return list;
    }
}
