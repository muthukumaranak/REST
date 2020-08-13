package com.example.blogapp.repository;

import com.example.blogapp.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RegisterRepo extends JpaRepository<Registration, Integer> {

    @Query(value = "SELECT password from blogapp.users where email=?1",nativeQuery = true)
    String validateemail(String name);

    @Query(value = "SELECT name from blogapp.users where email=?1",nativeQuery = true)
    String getName(String name);


}
