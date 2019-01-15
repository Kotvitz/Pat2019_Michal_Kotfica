package com.example.kotvitz.michalkotfica;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button logoutButton = findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logOut();
            }
        });
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    private void logOut() {
        final Intent loginScreen = new Intent(this, LoginActivity.class);
        final SharedPreferences preferences = getSharedPreferences("loginData", Activity.MODE_PRIVATE);
        final LoginDataStorage dataStorage = new LoginDataStorage(preferences);
        Toast.makeText(getBaseContext(), "You have been logged out.", Toast.LENGTH_LONG).show();
        dataStorage.removeData();
        finish();
        startActivity(loginScreen);
    }
}
