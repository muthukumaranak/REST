package com.example.blogapp.controller;


import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BlogController {

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
    public String loginpage(){
        return "loginpage";
    }

    @RequestMapping("/myblogs")
    public String myblogs(){
        return "myblogs";
    }

    @RequestMapping("/registrationpage")
    public String registrationpage(){
        return "registrationpage";
    }

    @RequestMapping("/users")
    public String users(){
        return "users";
    }
}
