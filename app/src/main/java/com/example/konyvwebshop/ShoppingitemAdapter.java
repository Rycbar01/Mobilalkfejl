package com.example.konyvwebshop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ShoppingitemAdapter extends RecyclerView.Adapter<ShoppingitemAdapter.ViewHolder> {

    private ArrayList<Shoppingitem> mShoppingItemsData;
    private ArrayList<Shoppingitem> mShoppingItemsDataAll;
    private Context mContext;
    private int lastPosition = -1;

    ShoppingitemAdapter(Context context, ArrayList<Shoppingitem> itemsData){
        this.mShoppingItemsData = itemsData;
        this.mShoppingItemsDataAll = itemsData;
        this.mContext = context;

    }

    @Override
    public ShoppingitemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.lista_elemek, parent, false));
    }

    @Override
    public void onBindViewHolder(ShoppingitemAdapter.ViewHolder holder, int position) {
        Shoppingitem currentItem = mShoppingItemsData.get(position);
        holder.bindTo(currentItem);

    }

    @Override
    public int getItemCount() {
        return mShoppingItemsData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bindTo(Shoppingitem currentItem) {
        }
    }
}

