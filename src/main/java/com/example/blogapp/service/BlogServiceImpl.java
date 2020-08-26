package com.example.blogapp.service;

import com.example.blogapp.entity.BlogPost;
import com.example.blogapp.repository.BlogPostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogInterfaceService{


    @Autowired
    private BlogPostRepo blogPostRepo;

    @Override
    public List<BlogPost> getAllEmployees() {
        return blogPostRepo.findAll();
    }


    @Override
    public Page<BlogPost> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.blogPostRepo.findAll(pageable);
    }
}
