package com.codingnomads.impacttracker.logic;

import org.hibernate.validator.constraints.Length;

import java.util.Set;

public class User {

    private Integer id;
    private String username;
    @Length(min = 7, message = "*Your password must have at least 7 characters")
    private String password;

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
