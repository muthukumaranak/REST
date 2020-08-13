package com.example.blogapp.entity;

import javax.persistence.*;

@Entity
@Table(name = "blogs")
public class BlogPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int id;

    @Column(name = "email")
    public String email;

    @Column(name = "title")
    public String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "blogcontent")
    public String blogcontent;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public BlogPost(String email, String title, String blogcontent) {
        this.email = email;
        this.title = title;
        this.blogcontent = blogcontent;
    }

    public BlogPost() {
    }

    public BlogPost(int id, String email, String title, String blogcontent) {
        this.id = id;
        this.email = email;
        this.title = title;
        this.blogcontent = blogcontent;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBlogcontent() {
        return blogcontent;
    }

    public void setBlogcontent(String blogcontent) {
        this.blogcontent = blogcontent;
    }

    @Override
    public String toString() {
        return "addBlog{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", title='" + title + '\'' +
                ", blogcontent='" + blogcontent + '\'' +
                '}';
    }
}
