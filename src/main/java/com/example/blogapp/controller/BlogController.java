package com.example.blogapp.controller;

import com.example.blogapp.entity.Comment;
import com.example.blogapp.entity.BlogPost;
import com.example.blogapp.repository.UsersRepo;
import com.example.blogapp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BlogController {

    @Autowired
    UserService userService;

    @Autowired
    TagsService tagsService;

    @Autowired
    UsersRepo usersRepo;

    @Autowired
    LoginService loginService;

    @Autowired
    CommentService commentService;

    @Autowired
    BlogService blogService;

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
        List<BlogPost> list = (List<BlogPost>) blogService.getall();
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
                List<BlogPost> list = (List<BlogPost>) blogService.getall();
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
        List<BlogPost> list = (List<BlogPost>) blogService.getall();
        model.addAttribute("list", list);
        List<Comment> commentList = commentService.getAll();
        model.addAttribute("commentList",commentList);
        return "loginpage";
    }

    @RequestMapping("/displayallblogs")
    public String displayallblogs(Model model){
        model.addAttribute("name",sessionName);
            List<BlogPost> list = (List<BlogPost>) blogService.getall();
            model.addAttribute("list", list);
        List<Comment> commentList = commentService.getAll();
        model.addAttribute("commentList",commentList);
            return "allblogs";
    }

    @RequestMapping("/allusers")
    public String allusers(Model model){
            List<Object[]> list = userService.getall();
            model.addAttribute("list",list);
        return "users";
    }

    @RequestMapping("/adminmodulecheck")
    public String adminmodulecheck(@RequestParam String username, @RequestParam String password){
        sessionName = "Admin";
        String result = loginService.adminlogincheck(username,password);
        if(result.equals("positive"))
            return "adminmodule";
        return "adminloginerror";
    }

    @RequestMapping("/myblogs")
    public String myblogs(Model model){
        model.addAttribute("name",sessionName);
        List<BlogPost> list = (List<BlogPost>) blogService.getall();
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
        String result = userService.add(name,email,password);
        if(result.equals("positive"))
            return "index";
        else
            return "errorpage";
    }

    @PostMapping("/addingblog")
    public String addingblog(Model model,@RequestParam String title, @RequestParam String excerpt, @RequestParam String blogcontent){
        int blogId = blogService.addBlog(sessionName, sessionEmail, title, blogcontent, excerpt);
        model.addAttribute("name",sessionName);
        List<BlogPost> list = (List<BlogPost>) blogService.getall();
        model.addAttribute("list", list);
        List<Comment> commentList = commentService.getAll();
        model.addAttribute("commentList",commentList);
        tagsService.addTags(excerpt);
        return "loginpage";
    }

    @PostMapping("/addingComment")
    public String addingComment(Model model, @RequestParam String blogid, @RequestParam String comment){
        int bid = Integer.parseInt(blogid);
        commentService.addComment(bid, comment, sessionName);
        model.addAttribute("name",sessionName);
        List<BlogPost> list = (List<BlogPost>) blogService.getall();
        model.addAttribute("list", list);
        List<Comment> commentList = commentService.getAll();
        model.addAttribute("commentList",commentList);
        return "loginpage";
    }

    @PostMapping("/like")
    public String like(Model model, @RequestParam String id, @RequestParam String likes){
        int bid = Integer.parseInt(id);
        int like = Integer.parseInt(likes) + 1;
        blogService.addLike(bid, like);
        model.addAttribute("name",sessionName);
        List<BlogPost> list = (List<BlogPost>) blogService.getall();
        model.addAttribute("list", list);
        List<Comment> commentList = commentService.getAll();
        model.addAttribute("commentList",commentList);
        return "loginpage";
    }

    @RequestMapping("/updateblog")
    public String updateblog(Model model, @RequestParam int blogid, @RequestParam String blogcontent){
        blogService.updateblog(blogid,blogcontent);
        model.addAttribute("name",sessionName);
        List<BlogPost> list = (List<BlogPost>) blogService.getall();
        model.addAttribute("list", list);
        List<Comment> commentList = commentService.getAll();
        model.addAttribute("commentList",commentList);
        return "loginpage";
    }

    @GetMapping("/search")
    public String searching(){
        return "search";
    }

    @PostMapping("/searching")
    public String search(Model model, @RequestParam String search){
        System.err.println(search);
        List<BlogPost> list = blogService.search(search);
        if(list.isEmpty()){
            model.addAttribute("yesEmpty","No Results Found");
        }
        model.addAttribute("list",list);
        List<Comment> commentList = commentService.getAll();
        model.addAttribute("commentList",commentList);
        return "searchresult";
    }

    @RequestMapping("/removeBlog")
    public String removeBlog(Model model, @RequestParam int blogid){
        blogService.removeBlog(blogid);
        model.addAttribute("name",sessionName);
        List<BlogPost> list = (List<BlogPost>) blogService.getall();
        model.addAttribute("list", list);
        List<Comment> commentList = commentService.getAll();
        model.addAttribute("commentList",commentList);
        return "myblogs";
    }

    @RequestMapping("/removeBlogAsAdmin")
    public String removeBlogAsAdmin(Model model, @RequestParam int blogid){
        blogService.removeBlog(blogid);
        model.addAttribute("name",sessionName);
        List<BlogPost> list = (List<BlogPost>) blogService.getall();
        model.addAttribute("list", list);
        List<Comment> commentList = commentService.getAll();
        model.addAttribute("commentList",commentList);
        return "allblogs";
    }

}
