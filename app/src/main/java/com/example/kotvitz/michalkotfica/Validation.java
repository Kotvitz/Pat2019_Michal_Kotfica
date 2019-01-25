package com.example.kotvitz.michalkotfica;

class Validation {
    private static final String emailRegex = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
    private static final String passwdRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";

    public static boolean validateEmail(String email) {
        return email.matches(emailRegex);
    }

    public static boolean validatePassword(String passwd) {
        return passwd.matches(passwdRegex);
    }
}
