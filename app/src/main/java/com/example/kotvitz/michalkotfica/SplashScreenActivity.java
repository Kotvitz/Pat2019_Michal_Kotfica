package com.example.kotvitz.michalkotfica;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        final Button button = findViewById(R.id.button);
        final Intent mainScreen = new Intent(this, MainActivity.class);
        final Handler handler = new Handler();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.removeMessages(0);
            }
        });
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
                startActivity(mainScreen);
            }
        }, 5000);
    }

}
