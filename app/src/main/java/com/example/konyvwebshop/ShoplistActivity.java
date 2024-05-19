package com.example.konyvwebshop;

import static androidx.core.content.PackageManagerCompat.LOG_TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RatingBar;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ShoplistActivity extends AppCompatActivity {
    private static final String LOG_TAG = ShoplistActivity.class.getName();
    private static final int SECRET_KEY = 98;
    private FirebaseUser user;
    private FirebaseAuth mAuth;
    private RecyclerView mRecycleView;
    private ArrayList<Shoppingitem> mitemlist;
    private ShoppingitemAdapter mAdapter;
    private int getCartItems = 0;
    private int gridnumber = 1;
    private boolean viewRow = true;

    private FirebaseFirestore mFirestore;
    private CollectionReference mitems;

    private ArrayList<Shoppingitem> mitemsdata;
    private SharedPreferences preferences;
    private int cartItems = 0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoplist);


        int key = getIntent().getIntExtra("SECRET_KEY", 0);

        if (key != 98) {
            finish();
        }

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

        mFirestore = FirebaseFirestore.getInstance();
        mitems = mFirestore.collection("items");

        queryData();


        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_POWER_CONNECTED);

    }

    private void queryData(){
        mitemsdata.clear();

        //mitems.whereEqualTo()
        mitems.orderBy("name").limit(10).get().addOnSuccessListener(queryDocumentSnapshots -> {
            for(QueryDocumentSnapshot document : queryDocumentSnapshots){
                Shoppingitem item = document.toObject(Shoppingitem.class);
                mitemsdata.add(item);
                Log.d(LOG_TAG, "queryData1: " + item);
            }
            Log.d(LOG_TAG, "queryData2: " + mitemsdata.size());
            if(mitemsdata.size() ==0){
                intializeData();
                queryData();
            }
            mAdapter.notifyDataSetChanged();
        });
    }

    private void intializeData() {
        String[] itemlist=getResources().getStringArray(R.array.shoppingitemsname);
        String[] iteminfos=getResources().getStringArray(R.array.shoppingitemleiras);
        String[] itemprice=getResources().getStringArray(R.array.shoppingitemprice);
        TypedArray itemsimageresource=getResources().obtainTypedArray(R.array.shoppingitemimage);
        TypedArray itemsrate=getResources().obtainTypedArray(R.array.shoppingrates);



        //mitemlist.clear();

        for(int i=0;i<itemlist.length;i++){
            mitems.add(new Shoppingitem(itemlist[i], iteminfos[i], itemprice[i], itemsrate.getFloat(i, 0), itemsimageresource.getResourceId(i,0), 0));
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
            public boolean onQueryTextSubmit(String query) {return false;}

            public boolean onQueryTextChange(String s) {
                Log.d(LOG_TAG, s);                          //itt LOG_TAG van a vidiben
                mAdapter.getFilter().filter(s);
                return false;
            }
        });
        return true;
    }

//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.logout:
//                Log.d(LOG_TAG, "Kijelentkezés");
//                FirebaseAuth.getInstance().signOut();
//                finish();
//                return true;
//            case R.id.addcart:
//                Log.d(LOG_TAG, "Kosárhoz adás!");
//                return true;
//            default:
//        }
//        return super.onOptionsItemSelected(item);


//    private void changeSpanCount(MenuItem item, int drawableid, int spanCount) {
//        viewRow = !viewRow;
//        item.setIcon(drawableid);
//        GridLayoutManager layoutManager = (GridLayoutManager) mRecycleView.getLayoutManager();
//        layoutManager.setSpanCount(spanCount);
//    }
//
//    public boolean onPrepareOptionsMenu(Menu menu){
//        final MenuItem alertMenuItem = menu.findItem(R.id.addcart);
//        FrameLayout rootView = (FrameLayout) alertMenuItem.getActionView();
//
//        redCircle=(FrameLayout) rootView.findViewById(R.id.view_alert_red_circle);
//        contentTextView=(TextView) rootView.findViewById(R.id.view_alert_count_textview);
//        rootView.setOnClickListener((new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onOptionsItemSelected(alertMenuItem);
//            }
//        }));
//
//    return super.onPrepareOptionsMenu(menu);
//}
//public void updateAlertIcon(){
//    cartItems = (cartItems + 1);
//    if(0<cartItems){
//        contentTextView.setText(String.valueOf(cartItems));
//    } else {
//        contentTextView.setText("");
//    }
//}
    }

//    }
