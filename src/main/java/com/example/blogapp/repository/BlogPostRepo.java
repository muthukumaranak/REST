package com.example.blogapp.repository;

import com.example.blogapp.entity.BlogPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;


public interface BlogPostRepo extends JpaRepository<BlogPost, Integer> {


    @Transactional
    @Modifying
    @Query(value = "update posts set blogcontent=?1 where id=?2",nativeQuery = true)
    void updateblogs(String blogcontent,int blogid);

    @Transactional
    @Modifying
    @Query(value = "select * from posts where authorname ilike %?1% or title ilike %?1% or excerpt ilike %?1% or time ilike %?1%",nativeQuery = true)
    List<BlogPost> search(String search);

    @Query(value = "select * from posts where authorname=?1 or excerpt=?1 or time=?1",nativeQuery = true)
    Page<BlogPost> findAllByName(Pageable pageable, String filtername);

    @Query(value = "select email from posts where id=?1",nativeQuery = true)
    String email(int id);

    List<BlogPost> findAllById(int blogId);

    @Query(value = "select * from posts where authorname ilike %?1% and blogcontent ilike %?2% and excerpt ilike %?3%",nativeQuery = true)
    List<BlogPost> filter(String name, String content, String excerpt);

    @Query(value = "select * from posts where authorname ilike %?1% or blogcontent ilike %?1% or excerpt ilike %?1% or time ilike %?1%\n" +
            "and authorname ilike %?2% and blogcontent ilike %?3% and excerpt ilike %?4%", nativeQuery = true)
    List<BlogPost> filterAndSearch(String keyword, String name, String content, String excerpt);

    @Query(value = "select * from posts where email=?1",nativeQuery = true)
    List<BlogPost> findAllByEmail(String email);
}
