package com.example.konyvwebshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class ShoplistActivity extends AppCompatActivity {
    private static final String LOG_TAG = ShoplistActivity.class.getName();
    private FirebaseUser user;
    private FirebaseAuth mAuth;
    private RecyclerView mRecycleView;
    private ArrayList<Shoppingitem> mitemlist;
    private ShoppingitemAdapter mAdapter;
    private int gridnumber = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoplist);
        mAuth = FirebaseAuth.getInstance();

        user = FirebaseAuth.getInstance().getCurrentUser();

        if(user != null){
            Log.d(LOG_TAG, "Hitelesített felhasználó");
        } else {
            Log.d(LOG_TAG, "Nem hitelesített felhasználó");
            finish();
        }

        mRecycleView = findViewById(R.id.recycleview);
        mRecycleView.setLayoutManager(new GridLayoutManager(this, gridnumber));
        mitemlist=new ArrayList<>();
        mAdapter=new ShoppingitemAdapter(this, mitemlist);
        mRecycleView.setAdapter(mAdapter);
        intializeData();


    }

    private void intializeData() {
        String[] itemlist;
        String[] iteminfos;
        String[] itemprice;

        TypedArray itemsimageresource;
        TypedArray itemsrate;
    }
}