package com.example.blogapp.service;


import com.example.blogapp.entity.PostTags;
import com.example.blogapp.repository.PostTagsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostTagsService {

    @Autowired
    PostTagsRepo postTagsRepo;

    public void addTags(int tagId, String time) {
        PostTags postTags = new PostTags(tagId,time,time);
        postTagsRepo.save(postTags);
    }
}
