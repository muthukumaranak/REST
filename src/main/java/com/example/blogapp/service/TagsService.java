package com.example.blogapp.service;


import com.example.blogapp.entity.Tags;
import com.example.blogapp.repository.TagsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class TagsService {

    @Autowired
    TagsRepo tagsRepo;

    @Autowired
    PostTagsService postTagsService;

    public void addTags(String excerpt) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String time = f.format(new Date());
        String allTags[] = excerpt.split(",");

        for(String tag: allTags){
            Tags tags = new Tags(tag,time,time);
            tagsRepo.save(tags);
            int tagId = tags.getId();
            postTagsService.addTags(tagId,time);
        }

    }
}
