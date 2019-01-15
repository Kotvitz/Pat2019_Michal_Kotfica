package com.example.kotvitz.michalkotfica;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final Intent mainScreen = new Intent(this, MainActivity.class);
        final EditText emailText = findViewById(R.id.emailEditText);
        final EditText passwdText = findViewById(R.id.passwdEditText);
        Button loginButton = findViewById(R.id.loginButton);
        final Validation validation = new Validation();
        final SharedPreferences preferences = getSharedPreferences("loginData", Activity.MODE_PRIVATE);
        final LoginDataStorage dataStorage = new LoginDataStorage(preferences);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean allIsValid = validation.validate(emailText, passwdText, getBaseContext());
                if (allIsValid) {
                    dataStorage.saveData(emailText, passwdText);
                    finish();
                    startActivity(mainScreen);
                }
            }
        });
    }

    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
