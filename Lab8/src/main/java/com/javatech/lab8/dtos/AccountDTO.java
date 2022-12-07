package com.javatech.lab8.dtos;

import java.io.Serializable;

public class AccountDTO implements Serializable {
    Long id;
    String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
