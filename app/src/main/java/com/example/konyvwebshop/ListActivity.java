package com.example.konyvwebshop;

import androidx.annotation.NonNull;
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
import android.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    private static final String TAG = ListActivity.class.getName(); //MainActivity-ről Listre változtattam
    private static final int SECRET_KEY = 98;
    private FirebaseUser user;
    private FirebaseAuth mAuth;
    private FirebaseFirestore mfirestore;
    private CollectionReference mitems;
    private RecyclerView mRecycleView;
    private int gridnumber = 1;
    private ArrayList<Shoppingitem> mitemlist;
    private ShoppingitemAdapter mAdapter;

    private Notification mNotification;


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
            Log.d(TAG, "Hitelesített felhasználó");
        } else {
            Log.d(TAG, "Nem hitelesített felhasználó");
            finish();
        }

        mRecycleView = findViewById(R.id.recycleview);
        mRecycleView.setLayoutManager(new GridLayoutManager(this, gridnumber));
        mitemlist=new ArrayList<>();
        mAdapter=new ShoppingitemAdapter(this, mitemlist);
        mRecycleView.setAdapter(mAdapter);
        mfirestore = FirebaseFirestore.getInstance();
        mitems = mfirestore.collection("Items");

        mNotification = new Notification(this);

        queryData();
    }

    private void queryData() {
        mitemlist.clear();
        mitems.limit(12).get().addOnSuccessListener(queryDocumentSnapshots -> {
            for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                Shoppingitem item = document.toObject(Shoppingitem.class);
                item.setID(document.getId());
                mitemlist.add(item);
                Log.d(TAG, "queryData1: " + item);
            }
            Log.d(TAG, "queryData2: " + mitemlist.size());
            if (mitemlist.size() == 0) {
                intializeData();
                queryData();
            }
            mAdapter.notifyDataSetChanged();
        });
    }


    private void intializeData() {
        String[] itemlist = getResources().getStringArray(R.array.shoppingitemsname);
        String[] iteminfos = getResources().getStringArray(R.array.shoppingitemleiras);
        String[] itemprice = getResources().getStringArray(R.array.shoppingitemprice);
        TypedArray itemsimageresource = getResources().obtainTypedArray(R.array.shoppingitemimage);
        TypedArray itemsrate = getResources().obtainTypedArray(R.array.shoppingrates);

        Log.d(TAG, "intializeData: " + itemlist + iteminfos + itemprice + ", itemsimageresource: " + itemsimageresource + itemsrate);

        for(int i=0;i<itemlist.length;i++){
            mitems.add(new Shoppingitem(
                    itemlist[i],
                    iteminfos[i],
                    itemprice[i],
                    itemsrate.getFloat(i, 0),
                    itemsimageresource.getResourceId(i,0),
                    10));
        }

        itemsimageresource.recycle();
    }

    public void updateCount(Shoppingitem item){

        if(item.getCount() > 0){
            mitems.document(item._getID()).update("count", item.getCount() - 1)
                    .addOnFailureListener(fail -> {
                        Toast.makeText(this, item._getID(), Toast.LENGTH_LONG).show();
                    });

            queryData();

            mNotification.send(item.getName());
        } else {
            Toast.makeText(this, "A könyv elfogyott.", Toast.LENGTH_LONG).show();
        }

    }

    public void delete(Shoppingitem item) {
        DocumentReference ref = mitems.document(item._getID());
        ref.delete()
                .addOnSuccessListener(success -> {
                    Toast.makeText(this, item._getID(), Toast.LENGTH_LONG).show();

                })
                .addOnFailureListener(fail -> {
                    Toast.makeText(this, item._getID(), Toast.LENGTH_LONG).show();
                });

        queryData();
    }

    @Override
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

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.isEmpty()) {
                    queryData();
                } else {
                    searchBooks(newText);
                }
                return true;
            }
        });
        return true;
    }

    private void searchBooks(String searchText) {
        mitemlist.clear();
        mitems.whereEqualTo("name", searchText)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                        Shoppingitem item = document.toObject(Shoppingitem.class);
                        item.setID(document.getId());
                        mitemlist.add(item);
                    }
                    mAdapter.notifyDataSetChanged();
                })
                .addOnFailureListener(e -> {
                    Log.e(TAG, "Error searching books: ", e);
                });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.logout) {
            FirebaseAuth.getInstance().signOut();
            finish();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}