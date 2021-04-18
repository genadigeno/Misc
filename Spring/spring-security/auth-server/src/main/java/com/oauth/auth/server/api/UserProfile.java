package com.oauth.auth.server.api;

public class UserProfile {

    public UserProfile() {}

    public UserProfile(String name, String email) {
        this.name = name;
        this.email = email;
    }

    private String name;

    private String email;

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
}
