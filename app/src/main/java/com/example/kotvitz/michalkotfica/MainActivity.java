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

    private RecyclerView rvElements;
    private ElementsAdapter adapter;
    private Intent loginScreen;
    private SharedPreferences preferences;
    private Button logoutButton;
    private LoginDataStorage dataStorage;
    private ArrayList<ElementInfo> elements = null;
    private ProgressDialog dialog;
    private final ArrayParser parser = new ArrayParser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvElements = findViewById(R.id.rvElements);
        loginScreen = new Intent(this, LoginActivity.class);
        preferences = getSharedPreferences(AppConstant.LOGIN_PREFS, Activity.MODE_PRIVATE);
        dataStorage = new LoginDataStorage(preferences);
        logoutButton = findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logOut();
            }
        });
        getData(AppConstant.JSON_ARRAY);
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    private void logOut() {
        Toast.makeText(getBaseContext(), getString(R.string.log_out_message), Toast.LENGTH_LONG).show();
        dataStorage.removeData();
        finish();
        startActivity(loginScreen);
    }

    private void getData(String json) {
        dialog = new ProgressDialog(this);
        dialog.setMessage(getString(R.string.dialogLoading));
        dialog.show();

        StringRequest request = new StringRequest(json, new Response.Listener<String>() {
            @Override
            public void onResponse(String string) {
                buildList(string);
                dialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getApplicationContext(), getString(R.string.volleyErrorLabel)
                        + volleyError.networkResponse, Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        RequestQueue rQueue = Volley.newRequestQueue(MainActivity.this);
        rQueue.add(request);
    }

    private void buildList(String json) {
        elements = parser.parseElementsArray(json);
        configureView();
    }

    private void configureView() {
        adapter = new ElementsAdapter(elements);
        rvElements.setAdapter(adapter);
        rvElements.setLayoutManager(new LinearLayoutManager(this));
    }
}
