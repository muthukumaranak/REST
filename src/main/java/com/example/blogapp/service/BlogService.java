package com.example.blogapp.service;

import com.example.blogapp.entity.BlogPost;
import org.springframework.data.domain.Page;

public interface BlogService {
    Page<BlogPost> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
