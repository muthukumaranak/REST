package com.example.blogapp.service;

public class CommentDTO {

    public int id;
    public String commentby;
    public String Comment;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCommentby() {
        return commentby;
    }

    public void setCommentby(String commentby) {
        this.commentby = commentby;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }


}
