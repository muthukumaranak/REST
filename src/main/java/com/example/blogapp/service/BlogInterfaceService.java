package com.example.blogapp.service;

import com.example.blogapp.entity.BlogPost;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BlogInterfaceService {

    List<BlogPost> getAllEmployees();
    Page<BlogPost> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
