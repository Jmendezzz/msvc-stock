package com.emazon.msvc.stock.msvcstock.domain.models;

public class User {
    private Long id;
    private String email;
    private String role;

    public User() {
    }

    public User(Long id, String email, String role) {
        this.id = id;
        this.email = email;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }
}
