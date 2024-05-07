package com.example.konyvwebshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getName();
    private static final int SECRET_KEY = 99;

    EditText userNET;
    EditText passWET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userNET = findViewById(R.id.edittextuserN);
        passWET = findViewById(R.id.edittextpassword);


        Log.i(LOG_TAG, "onCreate");
    }

    public void bejelentkezes(View view) {

    String userN = userNET.getText().toString();
    String passW = passWET.getText().toString();

    Log.i(LOG_TAG, "Bejelentkezett: " + userN + ", jelszó: " + passW);
    }

    public void regiszt(View view) {
        Intent intent = new Intent(this, RegisztActivity.class);
        intent.putExtra("SECRET_KEY", 99);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(LOG_TAG, "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(LOG_TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(LOG_TAG, "onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(LOG_TAG, "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(LOG_TAG, "onResume");
    }
}