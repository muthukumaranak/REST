package com.example.blogapp.service;

import com.example.blogapp.entity.Tags;
import com.example.blogapp.repository.TagRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagService {

    @Autowired
    TagRepo tagRepo;

    @Autowired
    Tags tags;

    public int addTag(String text, String time) {
            tags = new Tags(text,time,time);
            tagRepo.save(tags);
            int tagId = tags.getId();
            return tagId;
    }
}
