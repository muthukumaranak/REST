package com.example.blogapp.repository;

import com.example.blogapp.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface UsersRepo extends JpaRepository<Users, Integer> {

    @Query(value = "SELECT password from users where email=?1",nativeQuery = true)
    String validateemail(String name);

    @Query(value = "SELECT name from users where email=?1",nativeQuery = true)
    String getName(String name);

    @Query(value = "SELECT role from users where email=?1",nativeQuery = true)
    String getRole(String name);

    Users findByEmail(String email);


}
