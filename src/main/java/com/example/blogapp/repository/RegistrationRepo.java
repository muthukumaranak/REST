package com.example.blogapp.repository;

import com.example.blogapp.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface RegistrationRepo extends JpaRepository<Registration, Integer> {

    @Query(value = "SELECT password from users where email=?1",nativeQuery = true)
    String validateemail(String name);

    @Query(value = "SELECT name from users where email=?1",nativeQuery = true)
    String getName(String name);

    @Query(value = "SELECT role from users where email=?1",nativeQuery = true)
    String getRole(String name);
}
