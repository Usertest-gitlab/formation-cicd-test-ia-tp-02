package com.devops.cicd.user;

public final class EmailValidator {

    private EmailValidator() {}

    public static boolean isValid(String email) {
        if (email == null) {
            return false;
        }

        String e = email.trim();
        if (e.length() < 8) {
            return false;
        }

        boolean hasArobase = false;
        boolean hasLower = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        for (char c : e.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasArobase = true;
            } else if (Character.isLowerCase(c)) {
                hasLower = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else {
                hasSpecial = true;
            }
        }

        return hasArobase && hasLower && hasDigit && hasSpecial;
    }
}
