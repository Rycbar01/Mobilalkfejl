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
import android.view.View;
import android.widget.FrameLayout;
import android.widget.SearchView;
import android.widget.TextView;

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
    private boolean viewRow = true;

    private int cartItems = 0;



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

    public boolean onOptionsItemSelected(MenuItem item){
        int itemId = item.getItemId();
        if (itemId == R.id.logout) {
            Log.d(LOG_TAG, "Log out clicked!");
            return true;
        } else if (itemId == R.id.settings) {
            Log.d(LOG_TAG, "Settings clicked!");
            return true;
        } else if (itemId == R.id.addcart) {
            Log.d(LOG_TAG, "Cart clicked!");
            return true;
        } else if (itemId == R.id.viewselector) {
            Log.d(LOG_TAG, "View selector clicked!");
            if(viewRow){
                changeSpanCount(item, R.drawable.viewgrid, 1);
            } else {
                changeSpanCount(item, R.drawable.view, 1);
            }
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void changeSpanCount(MenuItem item, int drawableid, int spanCount) {
        viewRow = !viewRow;
        item.setIcon(drawableid);
        GridLayoutManager layoutManager = (GridLayoutManager) mRecycleView.getLayoutManager();
        layoutManager.setSpanCount(spanCount);
    }


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
