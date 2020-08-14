package com.example.blogapp.controller;

import com.example.blogapp.entity.Comment;
import com.example.blogapp.entity.Registration;
import com.example.blogapp.entity.BlogPost;
import com.example.blogapp.service.CommentService;
import com.example.blogapp.service.LoginService;
import com.example.blogapp.service.RegisterService;
import com.example.blogapp.service.AddBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BlogController {

    @Autowired
    RegisterService registerService;

    @Autowired
    LoginService loginService;

    @Autowired
    CommentService commentService;

    @Autowired
    AddBlogService addBlogService;

    public String sessionName;
    public String sessionEmail;
    @RequestMapping("/index")
    public String index(){
        return "index";
    }


    @RequestMapping("/addblog")
    public String addblog(){
        return "addblog";
    }


    @RequestMapping("/adminlogin")
    public String adminlogin(){
        return "adminlogin";
    }

    @RequestMapping("/adminmodule")
    public String adminmodule(){
        return "adminmodule";
    }

    @RequestMapping("/allblogs")
    public String allblogs(){
        return "allblogs";
    }

    @RequestMapping("/loginpage")
        public String loginpage(Model model, @RequestParam String username, @RequestParam String password){
            sessionEmail = username;
            String result = loginService.logincheck(username,password);
            sessionName = loginService.getName(username);
            if(result.equals("positive")){
                model.addAttribute("name",sessionName);
                List<BlogPost> list = (List<BlogPost>)addBlogService.getall();
                model.addAttribute("list", list);

                List<Comment> commentList = commentService.getAll();
                model.addAttribute("commentList",commentList);

                return "loginpage";
            }
            return "indexerror";
    }

    @RequestMapping("/login")
    public String login(Model model){
        model.addAttribute("name",sessionName);
        List<BlogPost> list = (List<BlogPost>)addBlogService.getall();
        model.addAttribute("list", list);
        List<Comment> commentList = commentService.getAll();
        model.addAttribute("commentList",commentList);
        return "loginpage";
    }

    @RequestMapping("/displayallblogs")
    public String displayallblogs(Model model){
            List<BlogPost> list = (List<BlogPost>)addBlogService.getall();
            model.addAttribute("list", list);
        List<Comment> commentList = commentService.getAll();
        model.addAttribute("commentList",commentList);
            return "allblogs";
    }

    @RequestMapping("/allusers")
    public String allusers(Model model){
            List<Object[]> list = registerService.getall();
            model.addAttribute("list",list);
        return "users";
    }

    @RequestMapping("/adminmodulecheck")
    public String adminmodulecheck(@RequestParam String username, @RequestParam String password){
        String result = loginService.logincheck(username,password);
        if(result.equals("positive"))
            return "adminmodule";
        return "adminloginerror";
    }

    @RequestMapping("/myblogs")
    public String myblogs(){
        return "myblogs";
    }

    @RequestMapping("/registrationpage")
    public String registrationpage(){
        return "registrationpage";
    }



    @PostMapping("/register")
    public String register(@RequestParam String name, @RequestParam String email, @RequestParam String password ) {
        String result = registerService.add(name,email,password);
        if(result.equals("positive"))
            return "index";
        else
            return "errorpage";
    }

    @PostMapping("/addingblog")
    public String addingblog(Model model,@RequestParam String title, @RequestParam String blogcontent){
        addBlogService.addBlog(sessionName, sessionEmail, title, blogcontent);
        model.addAttribute("name",sessionName);
        List<BlogPost> list = (List<BlogPost>)addBlogService.getall();
        model.addAttribute("list", list);
        List<Comment> commentList = commentService.getAll();
        model.addAttribute("commentList",commentList);
        return "loginpage";
    }


    @PostMapping("/addingComment")
    public String addingComment(Model model, @RequestParam String blogid, @RequestParam String comment){
        int bid = Integer.parseInt(blogid);
        commentService.addComment(bid, comment, sessionName);
        model.addAttribute("name",sessionName);
        List<BlogPost> list = (List<BlogPost>)addBlogService.getall();
        model.addAttribute("list", list);
        List<Comment> commentList = commentService.getAll();
        model.addAttribute("commentList",commentList);
        return "loginpage";
    }
}
