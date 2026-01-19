package com.devops.cicd.user;

public class User {

    private final String email;
    private final String password;
    private final Role role;

    public User(String email, String password, Role role) {
        // TODO: appliquer toutes les règles de validation de la spec
        // - email: obligatoire, trim, format simple
        // - password: obligatoire, strong (PasswordPolicy.isStrong)
        // - role: obligatoire (non null)
        //
        // En cas d'erreur: IllegalArgumentException avec un message explicite
        // ("email must be valid", "password must be strong", "role must not be null")

        this.email = email;       // TODO: email doit être normalisé (trim)
        this.password = password; // TODO: password ne doit pas être modifié
        this.role = role;         // TODO: role non null
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
        // TODO: true uniquement si role == ADMIN
        return false;
    }

    // BONUS: vous pouvez ajouter equals/hashCode/toString si utile (non obligatoire)
}
