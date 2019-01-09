package com.example.kotvitz.michalkotfica;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
        Button loginButton = findViewById(R.id.button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean allIsValid = validate(emailText, passwdText);
                if (allIsValid) {
                    finish();
                    startActivity(mainScreen);
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

    public void onBackPressed() {
        moveTaskToBack(true);
    }

    public boolean validate(EditText editText1, EditText editText2) {
        String email = editText1.getText().toString();
        String passwd = editText2.getText().toString();
        final TextView emailFailed = findViewById(R.id.validateError);
        final TextView passwdFailed = findViewById(R.id.validateError2);
        boolean emailIsValid = email.matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$");
        boolean passwdIsValid = passwd.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$");
        if (emailIsValid && passwdIsValid) {
            return true;
        } else {
            emailFailed.setVisibility(emailIsValid ? View.GONE : View.VISIBLE);
            passwdFailed.setVisibility(passwdIsValid ? View.GONE : View.VISIBLE);
            return false;
        }
    }
}
