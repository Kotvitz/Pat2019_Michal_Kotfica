package com.example.kotvitz.michalkotfica;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final static String BASE_SERVER_URL = "http://10.0.2.2:8080";
    private RecyclerView rvElements;
    private ProgressDialog dialog;

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
        rvElements = findViewById(R.id.rvElements);
        final String jsonString = BASE_SERVER_URL + "/page_0.json";

        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.show();

        StringRequest request = new StringRequest(jsonString, new Response.Listener<String>() {
            @Override
            public void onResponse(String string) {
                parseJsonData(string);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getApplicationContext(), "Error: " + volleyError.networkResponse, Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        RequestQueue rQueue = Volley.newRequestQueue(MainActivity.this);
        rQueue.add(request);
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

    private void parseJsonData(String jsonString) {
        ArrayList<ElementInfo> elements = ElementInfo.createElementsList(jsonString);
        ElementsAdapter adapter = new ElementsAdapter(elements);
        rvElements.setAdapter(adapter);
        rvElements.setLayoutManager(new LinearLayoutManager(this));
        dialog.dismiss();
    }
}
