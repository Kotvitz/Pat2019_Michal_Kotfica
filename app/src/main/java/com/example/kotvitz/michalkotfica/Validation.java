package com.example.kotvitz.michalkotfica;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

public class Validation {
    private String emailRegex;
    private String passwdRegex;
    private String emailError;
    private String passwdError;

    public Validation() {
        this.emailRegex = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
        this.passwdRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";
        this.emailError = "The email address is not valid.";
        this.passwdError = "The password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter and one number.";
    }

    public boolean validate(EditText emailText, EditText passwdText, Context context) {
        String email = emailText.getText().toString();
        String passwd = passwdText.getText().toString();
        boolean emailIsValid = email.matches(emailRegex);
        boolean passwdIsValid = passwd.matches(passwdRegex);
        if (emailIsValid && passwdIsValid) {
            Toast.makeText(context, "Logged in successfully", Toast.LENGTH_LONG).show();
            return true;
        } else {
            if (!emailIsValid)
                emailText.setError(emailError);
            if (!passwdIsValid)
                passwdText.setError(passwdError);
            return false;
        }
    }
}
