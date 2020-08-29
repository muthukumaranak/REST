package com.example.blogapp.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "posts")
public class BlogPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int id;

    @Column(name = "authorname")
    public String authorname;

    @Column(name = "email")
    public String email;

    @Column(name = "title")
    public String title;

    @Column(name = "blogcontent")
    public String blogcontent;

    @Column(name = "likes")
    public int likes;

    @Column(name = "time")
    public String time;

    @Column(name = "excerpt")
    public String excerpt;

    @OneToMany(mappedBy = "blogPost", fetch = FetchType.EAGER, cascade = {CascadeType.ALL}, orphanRemoval = true, targetEntity = Comment.class)
    public List<Comment> commentList;

    public String getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthorname() {
        return authorname;
    }

    public void setAuthorname(String authorname) {
        this.authorname = authorname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBlogcontent() {
        return blogcontent;
    }

    public void setBlogcontent(String blogcontent) {
        this.blogcontent = blogcontent;
    }

    public BlogPost(int id, String authorname, String email, String title, String blogcontent, int likes, String time, String excerpt) {
        this.id = id;
        this.authorname = authorname;
        this.email = email;
        this.title = title;
        this.blogcontent = blogcontent;
        this.likes = likes;
        this.time = time;
        this.excerpt = excerpt;
    }

    public BlogPost(String authorname, String email, String title, String blogcontent, int likes, String time, String excerpt) {
        this.authorname = authorname;
        this.email = email;
        this.title = title;
        this.blogcontent = blogcontent;
        this.likes = likes;
        this.time = time;
        this.excerpt = excerpt;
    }

    public BlogPost() {
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }
}
