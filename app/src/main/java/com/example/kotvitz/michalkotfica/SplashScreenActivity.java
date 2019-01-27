package com.example.kotvitz.michalkotfica;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {

    private final Handler handler = new Handler();
    private LoginDataStorage dataStorage;
    private Intent loginScreen;
    private Intent mainScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        loginScreen = new Intent(this, LoginActivity.class);
        mainScreen = new Intent(this, MainActivity.class);
        SharedPreferences preferences = getSharedPreferences(AppConstant.LOGIN_PREFS, Activity.MODE_PRIVATE);
        dataStorage = new LoginDataStorage(preferences);
        goToAnotherActivityAfterDuration();
    }

    @Override
    public void onBackPressed() {
        //Remove a delayed messages with code 0
        handler.removeMessages(0);
    }

    private void goToAnotherActivityAfterDuration() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (dataStorage.isLoggedIn()) {
                    finish();
                    startActivity(mainScreen);
                } else {
                    finish();
                    startActivity(loginScreen);
                }
            }
        }, AppConstant.SPLASH_SCREEN_DURATION);
    }
}
