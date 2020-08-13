package com.example.blogapp.entity;

import javax.persistence.*;

@Entity
@Table(name = "blogs")
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

    public BlogPost(int id, String authorname, String email, String title, String blogcontent) {
        this.id = id;
        this.authorname = authorname;
        this.email = email;
        this.title = title;
        this.blogcontent = blogcontent;
    }

    public BlogPost(String authorname, String email, String title, String blogcontent) {
        this.authorname = authorname;
        this.email = email;
        this.title = title;
        this.blogcontent = blogcontent;
    }

    public BlogPost() {
    }

    @Override
    public String toString() {
        return "BlogPost{" +
                "id=" + id +
                ", authorname='" + authorname + '\'' +
                ", email='" + email + '\'' +
                ", title='" + title + '\'' +
                ", blogcontent='" + blogcontent + '\'' +
                '}';
    }
}
