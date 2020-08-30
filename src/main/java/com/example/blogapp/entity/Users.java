package com.example.blogapp.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@ApiModel(description = "User Details")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(notes = "Auto Generated ID")
    public int id;

    @ApiModelProperty(notes = "Name")
    @Column(name = "name")
    public String name;

    @ApiModelProperty(notes = "Email")
    @Column(name = "email")
    public String email;

    @ApiModelProperty(notes = "Password")
    @Column(name = "password")
    public String password;

    @ApiModelProperty(notes = "Roles")
    @Column(name = "role")
    public String role = "user";


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Users(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = "user";
    }

    public Users(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = "user";
    }

    public Users(String name, String email, String encode, String roles) {
    }

    public Users() {
    }
}
