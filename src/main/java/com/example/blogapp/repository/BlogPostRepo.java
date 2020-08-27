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

    @Query(value = "select authorname, count(authorname) as count FROM posts group by authorname order by authorname asc",nativeQuery = true)
    List<Object[]> getCount();

    @Transactional
    @Modifying
    @Query(value = "update posts set likes = ?1 where id = ?2",nativeQuery = true)
    void postLike(int likes, int bid);

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

}
