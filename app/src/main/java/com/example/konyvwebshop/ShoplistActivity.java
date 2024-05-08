package com.example.konyvwebshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ShoplistActivity extends AppCompatActivity {
    private static final String LOG_TAG = ShoplistActivity.class.getName();
    private FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoplist);

        user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){
            Log.d(LOG_TAG, "Hitelesített felhasználó");
        } else {
            Log.d(LOG_TAG, "Nem hitelesített felhasználó");
            finish();
        }
    }
}