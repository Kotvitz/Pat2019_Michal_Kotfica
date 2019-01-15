package com.example.kotvitz.michalkotfica;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import java.util.Objects;

public class SplashScreenActivity extends AppCompatActivity {

    private final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        final Intent loginScreen = new Intent(this, LoginActivity.class);
        final Intent mainScreen = new Intent(this, MainActivity.class);
        final SharedPreferences preferences = getSharedPreferences("loginData", Activity.MODE_PRIVATE);
        final LoginDataStorage dataStorage = new LoginDataStorage(preferences);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (Objects.equals(dataStorage.restoreEmail(), "") && Objects.equals(dataStorage.restorePassword(), "")) {
                    finish();
                    startActivity(loginScreen);
                } else {
                    finish();
                    startActivity(mainScreen);
                }
            }
        }, 5000);
    }

    @Override
    public void onBackPressed() {
        //Remove a delayed messages with code 0
        handler.removeMessages(0);
    }
}
