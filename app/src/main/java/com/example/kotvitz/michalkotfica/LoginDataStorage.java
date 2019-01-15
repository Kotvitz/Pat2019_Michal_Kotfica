package com.example.kotvitz.michalkotfica;

import android.content.SharedPreferences;
import android.widget.EditText;

public class LoginDataStorage {
    private SharedPreferences preferences;

    public LoginDataStorage(SharedPreferences preferences) {
        this.preferences = preferences;
    }

    public void saveData(EditText emailText, EditText passwdText) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("email", emailText.getText().toString());
        editor.putString("passwd", passwdText.getText().toString());
        editor.apply();
    }

    public void removeData() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove("email");
        editor.remove("passwd");
        editor.apply();
    }

    public String restoreEmail() {
        return preferences.getString("email", "");
    }

    public String restorePassword() {
        return preferences.getString("passwd", "");
    }
}
