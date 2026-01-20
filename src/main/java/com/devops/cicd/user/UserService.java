package com.devops.cicd.user;

import com.devops.cicd.PasswordPolicy;

public class UserService {

    /**
     * Enregistre un utilisateur à partir des paramètres.
     *
     * Règles (voir spec) :
     * - crée un User
     * - renvoie l'utilisateur créé
     * - propage les erreurs si les données sont invalides
     */
    public User register(String email, String password, Role role) {
        // Validation role
        if (role == null) {
            throw new IllegalArgumentException("role must not be null");
        }

        // Validation email
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("email must be valid");
        }

        String trimmedEmail = email.trim();

        if (!isValidEmail(trimmedEmail)) {
            throw new IllegalArgumentException("email must be valid");
        }

        // Validation password
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("password must be strong");
        }

        if (!PasswordPolicy.isStrong(password)) {
            throw new IllegalArgumentException("password must be strong");
        }

        return new User(email, password, role);
    }
    
    private boolean isValidEmail(String email) {
        int atIndex = email.indexOf('@');

        // exactement un @
        if (atIndex < 1 || atIndex != email.lastIndexOf('@')) {
            return false;
        }

        // au moins un . après le @
        String domainPart = email.substring(atIndex + 1);
        return domainPart.contains(".");
    }
}
