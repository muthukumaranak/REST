package com.example.blogapp.controller;

import com.example.blogapp.entity.Comment;
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
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @RequestMapping("/guest")
    public String guest(Model model){
        List<BlogPost> list = (List<BlogPost>)addBlogService.getall();
        model.addAttribute("list", list);
        List<Comment> commentList = commentService.getAll();
        model.addAttribute("commentList",commentList);
        return "Guest";
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
        model.addAttribute("name",sessionName);
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
        sessionName = "Admin";
        String result = loginService.logincheck(username,password);
        if(result.equals("positive"))
            return "adminmodule";
        return "adminloginerror";
    }

    @RequestMapping("/myblogs")
    public String myblogs(Model model){
        model.addAttribute("name",sessionName);
        List<BlogPost> list = (List<BlogPost>)addBlogService.getall();
        model.addAttribute("list", list);
        List<Comment> commentList = commentService.getAll();
        model.addAttribute("commentList",commentList);
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

    @PostMapping("/like")
    public String like(Model model, @RequestParam String id, @RequestParam String likes){
        int bid = Integer.parseInt(id);
        int like = Integer.parseInt(likes) + 1;
        addBlogService.addLike(bid, like);
        model.addAttribute("name",sessionName);
        List<BlogPost> list = (List<BlogPost>)addBlogService.getall();
        model.addAttribute("list", list);
        List<Comment> commentList = commentService.getAll();
        model.addAttribute("commentList",commentList);
        return "loginpage";
    }

    @RequestMapping("/updateblog")
    public String updateblog(Model model, @RequestParam int blogid, @RequestParam String blogcontent){
        addBlogService.updateblog(blogid,blogcontent);
        model.addAttribute("name",sessionName);
        List<BlogPost> list = (List<BlogPost>)addBlogService.getall();
        model.addAttribute("list", list);
        List<Comment> commentList = commentService.getAll();
        model.addAttribute("commentList",commentList);
        return "loginpage";
    }

    @RequestMapping("/search")
    public String searching(){
        return "search";
    }
    @RequestMapping("/searching")
    public String search(Model model, @RequestParam String search){
        List<BlogPost> list = addBlogService.search(search);
        model.addAttribute("list",list);
        List<Comment> commentList = commentService.getAll();
        model.addAttribute("commentList",commentList);
        return "searchresult";
    }

    @RequestMapping("/removeBlog")
    public String removeBlog(Model model, @RequestParam int blogid){
        addBlogService.removeBlog(blogid);
        model.addAttribute("name",sessionName);
        List<BlogPost> list = (List<BlogPost>)addBlogService.getall();
        model.addAttribute("list", list);
        List<Comment> commentList = commentService.getAll();
        model.addAttribute("commentList",commentList);
        return "myblogs";
    }

    @RequestMapping("/removeBlogAsAdmin")
    public String removeBlogAsAdmin(Model model, @RequestParam int blogid){
        addBlogService.removeBlog(blogid);
        model.addAttribute("name",sessionName);
        List<BlogPost> list = (List<BlogPost>)addBlogService.getall();
        model.addAttribute("list", list);
        List<Comment> commentList = commentService.getAll();
        model.addAttribute("commentList",commentList);
        return "allblogs";
    }
}
