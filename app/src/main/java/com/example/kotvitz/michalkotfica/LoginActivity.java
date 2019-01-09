package com.example.kotvitz.michalkotfica;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = findViewById(R.id.toolbar);
        final Intent mainScreen = new Intent(this, MainActivity.class);
        final EditText emailText = findViewById(R.id.editText);
        final EditText passwdText = findViewById(R.id.editText2);
        final TextView emailFailed = findViewById(R.id.validateError);
        final TextView passwdFailed = findViewById(R.id.validateError2);
        Button loginButton = findViewById(R.id.button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailText.getText().toString();
                String passwd = passwdText.getText().toString();
                String emailRegex = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
                String passwdRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";
                if (email.matches(emailRegex) && passwd.matches(passwdRegex)) {
                    startActivity(mainScreen);
                } else {
                    if (!email.matches(emailRegex))
                        emailFailed.setVisibility(View.VISIBLE);
                    else
                        emailFailed.setVisibility(View.GONE);

                    if (!passwd.matches(passwdRegex))
                        passwdFailed.setVisibility(View.VISIBLE);
                    else
                        passwdFailed.setVisibility(View.GONE);
                }
            }
        });
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
