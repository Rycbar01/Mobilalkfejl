package com.example.konyvwebshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

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
        String[] itemlist=getResources().getStringArray(R.array.shoppingitemsname);
        String[] iteminfos=getResources().getStringArray(R.array.shoppingitemleiras);
        String[] itemprice=getResources().getStringArray(R.array.shoppingitemprice);
        TypedArray itemsimageresource=getResources().obtainTypedArray(R.array.shoppingitemimage);
        TypedArray itemsrate=getResources().obtainTypedArray(R.array.shoppingrates);

        mitemlist.clear();

        for(int i=0;i<itemlist.length;i++){
            mitemlist.add(new Shoppingitem(itemlist[i], iteminfos[i], itemprice[i], itemsrate.getFloat(i, 0), itemsimageresource.getResourceId(i,0)));
        }

        itemsimageresource.recycle();
        mAdapter.notifyDataSetChanged();
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.shoplistmenu, menu);
        MenuItem menuItem = menu.findItem(R.id.search_bar);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            public boolean onQueryTextChange(String s) {
                Log.d(LOG_TAG, s);
                mAdapter.getFilter().filter(s);
                return false;
            }
        });
        return true;
    }


    }
