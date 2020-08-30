package com.example.blogapp.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name = "Comment")
@ApiModel(description="Comment Details")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "Auto Generated ID for Comment")
    public int id;

    @ManyToOne
    @JoinColumn(name = "blogid", referencedColumnName = "id")
    @ApiModelProperty(notes = "Blog Id for which comment is created")
    public BlogPost blogPost;

    @ApiModelProperty(notes = "Comment")
    @Column(name = "comment")
    public String comment;

    @ApiModelProperty(notes = "Commented By")
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
