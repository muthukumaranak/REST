package com.example.blogapp.repository;

import com.example.blogapp.entity.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;


public interface BlogPostRepo extends JpaRepository<BlogPost, Integer> {

    @Query(value = "select authorname, count(authorname) as count FROM blogapp.blogs group by authorname order by authorname asc",nativeQuery = true)
    List<Object[]> getCount();

    @Transactional
    @Modifying
    @Query(value = "update blogapp.blogs set likes = ?1 where id = ?2",nativeQuery = true)
    void postLike(int likes, int bid);

    @Transactional
    @Modifying
    @Query(value = "update blogapp.blogs set blogcontent=?1 where id=?2",nativeQuery = true)
    void updateblogs(String blogcontent,int blogid);

    @Transactional
    @Modifying
    @Query(value = "select * from blogapp.blogs where title like %?1%",nativeQuery = true)
    List<BlogPost> search(String search);

    @Transactional
    @Modifying
    @Query(value = "select * from blogapp.blogs where excerpt=?1",nativeQuery = true)
    List<BlogPost> searchByTag(String search);
}
