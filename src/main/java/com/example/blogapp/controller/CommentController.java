package com.example.blogapp.controller;

import com.example.blogapp.service.CommentDTO;
import com.example.blogapp.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
@Api(value="Comment Page")
public class CommentController {

    @Autowired
    CommentService commentService;


    @ApiOperation(value = "Create a New Comment", response = String.class)
    @ApiParam(value = "BlogId, Email, Comment Required", required = true)
    @PostMapping
    public String create(@RequestParam String id, @RequestParam String email, @RequestParam String comment){
        return commentService.create(id,email,comment);
    }


    @ApiOperation(value = "Getting List of Comments", response = List.class)
    @ApiParam(value = "No Param's Required", required = false)
    @GetMapping
    public List<CommentDTO> retrieve(){
       return commentService.retrieve();
    }


    @ApiOperation(value = "Updating a comment", response = String.class)
    @ApiParam(value = "CommentID, Comment", required = true)
    @PutMapping
    public String update(@RequestParam String id, @RequestParam String comment){
        return commentService.update(id,comment);
    }

    @ApiOperation(value = "Deleting a comment", response = String.class)
    @ApiParam(value = "Comment ID", required = true)
    @DeleteMapping
    public String delete(@RequestParam String id){
        return commentService.delete(id);
    }

}
