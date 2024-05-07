package com.example.konyvwebshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class RegisztActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regiszt);

        int secret_key = getIntent().getIntExtra("SECRET_KEY", 0);

        if(secret_key!=99){
            finish();
        }
    }

    public void regiszt(View view) {
    }

    public void megse(View view) {
        finish();
    }
}