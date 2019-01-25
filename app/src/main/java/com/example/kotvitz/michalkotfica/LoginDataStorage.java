package com.example.kotvitz.michalkotfica;

import android.content.SharedPreferences;

class LoginDataStorage {
    private final SharedPreferences preferences;
    private static final String EMAIL_KEY = "email";
    private static final String PASSWD_KEY = "passwd";

    public LoginDataStorage(SharedPreferences preferences) {
        this.preferences = preferences;
    }

    public void saveData(String email, String passwd) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(EMAIL_KEY, email);
        editor.putString(PASSWD_KEY, passwd);
        editor.apply();
    }

    public void removeData() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(EMAIL_KEY);
        editor.remove(PASSWD_KEY);
        editor.apply();
    }

    public boolean isLoggedIn() {
        return preferences.contains(EMAIL_KEY) && preferences.contains(PASSWD_KEY);
    }
}
