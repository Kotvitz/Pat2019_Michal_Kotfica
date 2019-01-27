package com.example.kotvitz.michalkotfica;

import android.content.SharedPreferences;

class LoginDataStorage {
    private final SharedPreferences preferences;

    public LoginDataStorage(SharedPreferences preferences) {
        this.preferences = preferences;
    }

    public void saveData(String email, String passwd) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(AppConstant.EMAIL_KEY, email);
        editor.putString(AppConstant.PASSWD_KEY, passwd);
        editor.apply();
    }

    public void removeData() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(AppConstant.EMAIL_KEY);
        editor.remove(AppConstant.PASSWD_KEY);
        editor.apply();
    }

    public boolean isLoggedIn() {
        return preferences.contains(AppConstant.EMAIL_KEY) && preferences.contains(AppConstant.PASSWD_KEY);
    }
}
