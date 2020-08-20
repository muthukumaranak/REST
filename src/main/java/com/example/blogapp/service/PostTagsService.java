package com.example.blogapp.service;

import com.example.blogapp.entity.PostTags;
import com.example.blogapp.repository.PostTagsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PostTagsService {

    @Autowired
    PostTagsRepo postTagsRepo;

    @Autowired
    PostTags postTags;

    public void addTag(int tagId, String time) {
        postTags = new PostTags(tagId,time,time);
        postTagsRepo.save(postTags);
    }
}
