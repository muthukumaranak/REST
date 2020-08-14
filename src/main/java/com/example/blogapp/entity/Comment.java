package com.example.blogapp.entity;

import javax.persistence.*;

@Entity
@Table(name = "Comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @Column(name = "blogid")
    public int blogid;

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

    public int getBlogid() {
        return blogid;
    }

    public void setBlogid(int blogid) {
        this.blogid = blogid;
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

    public Comment(int blogid, String comment, String commentby) {
        this.blogid = blogid;
        this.comment = comment;
        this.commentby = commentby;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", blogid=" + blogid +
                ", comment='" + comment + '\'' +
                ", commentby='" + commentby + '\'' +
                '}';
    }
}
