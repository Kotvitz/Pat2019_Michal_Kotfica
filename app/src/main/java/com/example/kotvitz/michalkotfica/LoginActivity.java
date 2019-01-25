package com.example.kotvitz.michalkotfica;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final Intent mainScreen = new Intent(this, MainActivity.class);
        final EditText emailText = findViewById(R.id.emailEditText);
        final EditText passwdText = findViewById(R.id.passwdEditText);
        Button loginButton = findViewById(R.id.loginButton);
        final SharedPreferences preferences = getSharedPreferences("loginData", Activity.MODE_PRIVATE);
        final LoginDataStorage dataStorage = new LoginDataStorage(preferences);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean emailIsValid = Validation.validateEmail(emailText.getText().toString());
                boolean passwdIsValid = Validation.validatePassword(passwdText.getText().toString());
                if (emailIsValid && passwdIsValid) {
                    Toast.makeText(getBaseContext(), getString(R.string.log_in_message), Toast.LENGTH_LONG).show();
                    dataStorage.saveData(emailText.getText().toString(), passwdText.getText().toString());
                    finish();
                    startActivity(mainScreen);
                } else {
                    if (!emailIsValid)
                        emailText.setError(getString(R.string.emailError));
                    if (!passwdIsValid)
                        passwdText.setError(getString(R.string.passwdError));
                }
            }
        });
    }

    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
