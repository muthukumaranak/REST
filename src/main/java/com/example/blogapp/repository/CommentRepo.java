package com.example.blogapp.repository;

import com.example.blogapp.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

    @Transactional
    @Modifying
    @Query(value = "update comment set comment=?2 where id=?1", nativeQuery = true)
    void update(int id,String comment);

    @Transactional
    @Modifying
    @Query(value = "delete from comment where id=?1", nativeQuery = true)
    void delete(int commentId);

    @Query(value = "select email from comment where id=?1",nativeQuery = true)
    String findMail(int commentId);
}
