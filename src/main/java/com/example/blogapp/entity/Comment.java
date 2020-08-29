package com.example.blogapp.entity;

import javax.persistence.*;

@Entity
@Table(name = "Comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @ManyToOne
    @JoinColumn(name = "blogid", referencedColumnName = "id")
    public BlogPost blogPost;

    @Column(name = "comment")
    public String comment;

    @Column(name = "commentby")
    public String commentby;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BlogPost getBlogPost() {
        return blogPost;
    }

    public void setBlogPost(BlogPost blogPost) {
        this.blogPost = blogPost;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCommentby() {
        return commentby;
    }

    public void setCommentby(String commentby) {
        this.commentby = commentby;
    }

    public Comment(BlogPost blogPost, String comment, String commentby) {
        this.blogPost = blogPost;
        this.comment = comment;
        this.commentby = commentby;
    }

    public Comment() {
    }
}
