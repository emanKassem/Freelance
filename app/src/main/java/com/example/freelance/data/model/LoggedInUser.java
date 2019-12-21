package com.example.freelance.data.model;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class LoggedInUser {

    private String password;
    private String displayName;

    public LoggedInUser(String userId, String displayName) {
        this.password = userId;
        this.displayName = displayName;
    }

    public String getPassword() {
        return password;
    }

    public String getDisplayName() {
        return displayName;
    }
}
