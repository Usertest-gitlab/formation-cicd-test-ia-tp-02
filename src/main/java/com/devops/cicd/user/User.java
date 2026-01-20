package com.devops.cicd.user;

public class User {
    private final String email;
    private final String password;
    private final Role role;

    public User(String email, String password, Role role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }
    
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public boolean canAccessAdminArea() {
        return role == Role.ADMIN;
    }
}
