package com.example.demo.service;

import com.example.demo.model.User;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    public boolean validateCredentials(String username, String password) {
        // This is a simple validation for demo purposes
        // In production, you would query from a database
        return validateUser(username, password);
    }

    private boolean validateUser(String username, String password) {
        // Demo credentials
        if ("admin".equals(username) && "password123".equals(password)) {
            return true;
        }
        if ("user".equals(username) && "user123".equals(password)) {
            return true;
        }
        return false;
    }

    public User getUserByUsername(String username) {
        // Demo: Return a mock user
        if ("admin".equals(username)) {
            User user = new User("admin", "admin@example.com", "password123");
            user.setFirstName("Admin");
            user.setLastName("User");
            return user;
        }
        if ("user".equals(username)) {
            User user = new User("user", "user@example.com", "user123");
            user.setFirstName("John");
            user.setLastName("Doe");
            return user;
        }
        return null;
    }

    public User authenticateUser(String username, String password) {
        if (validateCredentials(username, password)) {
            return getUserByUsername(username);
        }
        return null;
    }
}
