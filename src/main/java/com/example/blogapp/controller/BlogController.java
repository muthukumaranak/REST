package com.example.blogapp.controller;

import com.example.blogapp.entity.Comment;
import com.example.blogapp.entity.BlogPost;
import com.example.blogapp.entity.Users;
import com.example.blogapp.repository.BlogPostRepo;
import com.example.blogapp.repository.CommentRepo;
import com.example.blogapp.repository.UsersRepo;
import com.example.blogapp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BlogController {

    @Autowired
    BlogServiceImpl blogServiceimpl;



    @Autowired
    TagsService tagsService;

    @Autowired
    UsersRepo usersRepo;

    @Autowired
    BlogPostRepo blogPostRepo;

    @Autowired
    CommentService commentService;

    @Autowired
    CommentRepo commentRepo ;

    @Autowired
    BlogService blogService;

    public String sessionName;

    public String sessionEmail;

    @RequestMapping("/login")
    public String index() {
        return "login";
    }

   // @RequestMapping(value = "/blog", method = RequestMethod.POST)
    @RequestMapping("/addblog")
    public String addblog() {
        return "addblog";
    }

    @RequestMapping("/guest")
    public Page<BlogPost> guest(Model model) {
        return findPaginated(1, "authorname", "asc", model);
    }

/*
    @RequestMapping("/allblogs")
    public String allblogs() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users users = usersRepo.findByEmail(auth.getName());
        System.out.println(users.getRole());
        if (users.getRole().equals("admin"))
            return "allblogs";
        else
            return "redirect:/loginpage";
    }

    @RequestMapping("/loginpage")
    public String loginpage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users users = usersRepo.findByEmail(auth.getName());
        if (users.getRole().equals("admin"))
            model.addAttribute("role","admin");
        sessionName = users.getName();
        sessionEmail = users.getEmail();
        model.addAttribute("name", sessionName);
        List<BlogPost> list = (List<BlogPost>) blogService.getall();
        model.addAttribute("list", list);
        List<Comment> commentList = commentService.getAll();
        model.addAttribute("commentList", commentList);
        return "loginpage";
    }



    @RequestMapping("/myblogs")
    public String myblogs(Model model) {
        model.addAttribute("name", sessionName);
        List<BlogPost> list = (List<BlogPost>) blogService.getall();
        model.addAttribute("list", list);
        List<Comment> commentList = commentService.getAll();
        model.addAttribute("commentList", commentList);
        return "myblogs";
    }

    @PostMapping("/CommentDeletion")
    public String deleteComment(@RequestParam int commentid){
        commentRepo.deleteById(commentid);
        return "redirect:/loginpage";
    }

    @RequestMapping("/registrationpage")
    public String registrationpage() {
        return "registrationpage";
    }

    @PostMapping("/registration")
    public String register(@RequestParam String name, @RequestParam String email, @RequestParam String password) {
       // String result = userService.add(name, email, password);

            return "errorpage";
    }

    @PostMapping("/addingblog")
    public String addingblog(Model model, @RequestParam String title, @RequestParam String excerpt, @RequestParam String blogcontent) {
        int blogId = blogService.addBlog(sessionName, sessionEmail, title, blogcontent, excerpt);
        model.addAttribute("name", sessionName);
        List<BlogPost> list = (List<BlogPost>) blogService.getall();
        model.addAttribute("list", list);
        List<Comment> commentList = commentService.getAll();
        model.addAttribute("commentList", commentList);
        tagsService.addTags(excerpt);
        return "loginpage";
    }

    @GetMapping("/addblogasguest")
    public String addblogasguest() {
        return "addblogasguest";
    }

    @PostMapping("/addingblogasguest")
    public Page<BlogPost> PostBlog(Model model, @RequestParam String name, @RequestParam String email, @RequestParam String title, @RequestParam String excerpt, @RequestParam String blogcontent) {
        blogService.addBlog(name, email, title, blogcontent, excerpt);
        return findPaginated(1, "authorname", "asc", model);
    }

    @PostMapping("/addingComment")
    public String addingComment(Model model, @RequestParam int blogid, @RequestParam String comment) {
        commentService.addComment(blogid, comment, sessionName);
        model.addAttribute("name", sessionName);
        List<BlogPost> list = (List<BlogPost>) blogService.getall();
        model.addAttribute("list", list);
        List<Comment> commentList = commentService.getAll();
        model.addAttribute("commentList", commentList);
        return "loginpage";
    }
*/
    @PostMapping("/addingComment2")
    public String addingComment2(Model model, @RequestParam int blogid, @RequestParam String name, @RequestParam String comment) {
        commentService.addComment(blogid, comment, name);
        model.addAttribute("name", sessionName);
        List<BlogPost> list = (List<BlogPost>) blogService.getall();
        model.addAttribute("list", list);
        List<Comment> commentList = commentService.getAll();
        model.addAttribute("commentList", commentList);
        return "redirect:/guest";
    }

    @RequestMapping("/updateblog")
    public String updateblog(Model model, @RequestParam int blogid, @RequestParam String blogcontent) {
        blogService.updateblog(blogid, blogcontent);
        model.addAttribute("name", sessionName);
        List<BlogPost> list = (List<BlogPost>) blogService.getall();
        model.addAttribute("list", list);
        List<Comment> commentList = commentService.getAll();
        model.addAttribute("commentList", commentList);
        return "loginpage";
    }

    @RequestMapping("/removeBlogAsAdmin")
    public String removeBlogAsAdmin(Model model, @RequestParam int blogid) {
        blogService.removeBlog(blogid);
        model.addAttribute("name", sessionName);
        List<BlogPost> list = (List<BlogPost>) blogService.getall();
        model.addAttribute("list", list);
        List<Comment> commentList = commentService.getAll();
        model.addAttribute("commentList", commentList);
        return "redirect:/loginpage";
    }

    @GetMapping("/paginated")
    public Page<BlogPost> viewHomePage(Model model) {
        return findPaginated(1, "authorname", "asc", model);
    }

    @GetMapping("/page/{pageNo}")
    public Page<BlogPost> findPaginated(@PathVariable(value = "pageNo") int pageNo, @RequestParam("sortField") String sortField,
                                        @RequestParam("sortDir") String sortDir, Model model) {
        int pageSize = 10;
        Page<BlogPost> page = blogServiceimpl.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<BlogPost> blogs = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("blogs", blogs);
        List<Comment> commentList = commentService.getAll();
        model.addAttribute("commentList", commentList);
        return page;
    }

    @GetMapping("/FilterByName")
    public String filterByName(Model model, @RequestParam String filterbyname) {
        return findPaginated2(1, "authorname", "asc", model, filterbyname);
    }

    public String findPaginated2(@PathVariable(value = "pageNo") int pageNo,
                                 @RequestParam("sortField") String sortField,
                                 @RequestParam("sortDir") String sortDir,
                                 Model model, String filerbyname) {
        int pageSize = 10;
        Page<BlogPost> page = blogServiceimpl.findPaginated2(pageNo, pageSize, sortField, sortDir, filerbyname);
        List<BlogPost> blogs = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("blogs", blogs);
        List<Comment> commentList = commentService.getAll();
        model.addAttribute("commentList", commentList);
        return "guest";
    }

    @RequestMapping("/delasGuest")
    public String delasGuest(@RequestParam int blogid, @RequestParam String email){
        String blogMail = blogPostRepo.email(blogid);
        if(email.equals(blogMail))
            blogPostRepo.deleteById(blogid);
        return "redirect:/guest";
    }
}
