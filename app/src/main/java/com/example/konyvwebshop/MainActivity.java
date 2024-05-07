package com.example.konyvwebshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void bejelentkezes(View view) {
        EditText userN = findViewById(R.id.edittextuserN);
        EditText passW = findViewById(R.id.edittextpassword);


    }
}