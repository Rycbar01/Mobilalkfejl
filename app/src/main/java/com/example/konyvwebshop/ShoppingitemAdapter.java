package com.example.konyvwebshop;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ShoppingitemAdapter extends RecyclerView.Adapter<ShoppingitemAdapter.ViewHolder>{

    @Override
    public ShoppingitemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ShoppingitemAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}




















//
//import android.content.Context;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Filter;
//import android.widget.Filterable;
//import android.widget.ImageView;
//import android.widget.RatingBar;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.bumptech.glide.Glide;
//
//import java.util.ArrayList;
//
//public class ShoppingitemAdapter extends RecyclerView.Adapter<ShoppingitemAdapter.ViewHolder> implements Filterable {
//
//    private ArrayList<Shoppingitem> mShoppingItemsData;
//    private ArrayList<Shoppingitem> mShoppingItemsDataAll;
//    private Context mContext;
//    private int lastPosition = -1;
//
//    ShoppingitemAdapter(Context context, ArrayList<Shoppingitem> itemsData){
//        this.mShoppingItemsData = itemsData;
//        this.mShoppingItemsDataAll = itemsData;
//        this.mContext = context;
//    }
//
//    @Override
//    public ShoppingitemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.lista_elemek, parent, false));
//    }
//
//    @Override
//    public void onBindViewHolder(ShoppingitemAdapter.ViewHolder holder, int position) {
//        Shoppingitem currentItem = mShoppingItemsData.get(position);
//        holder.bindTo(currentItem);
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return mShoppingItemsData.size();
//    }
//
//    @Override
//    public Filter getFilter() { return shoppingFilter; }
//    private Filter shoppingFilter = new Filter(){
//        @Override
//        protected FilterResults performFiltering(CharSequence charSequence){
//            ArrayList<Shoppingitem> filteredlist = new ArrayList<>();
//            FilterResults results = new FilterResults();
//
//            if(charSequence == null || charSequence.length() == 0){
//                results.count = mShoppingItemsDataAll.size();
//                results.values = mShoppingItemsDataAll;
//            } else {
//                String filterpattern = charSequence.toString().toLowerCase().trim();
//
//                for(Shoppingitem item : mShoppingItemsDataAll){
//                    if(item.getName().toLowerCase().contains(filterpattern)){
//                        filteredlist.add(item);
//                    }
//                }
//
//                results.count = filteredlist.size();
//                results.values = filteredlist;
//            }
//
//            return results;
//        }
//
//        @Override
//        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
//            mShoppingItemsData = (ArrayList) FilterResults.values;
//            notifyDataSetChanged();
//        }
//    };
//
//    class ViewHolder extends RecyclerView.ViewHolder {
//        private TextView mtitletex;
//        private TextView minfotext;
//        private TextView mpricetext;
//        private ImageView mitemimage;
//        private RatingBar mratingbar;
//
//
//
//        public ViewHolder(View itemView) {
//            super(itemView);
//
//            mtitletex = itemView.findViewById(R.id.itemtitle);
//            minfotext = itemView.findViewById(R.id.subtitle);
//            mpricetext = itemView.findViewById(R.id.price);
//            mitemimage = itemView.findViewById(R.id.itemimage);
//            mratingbar = itemView.findViewById(R.id.ratingbar);
//
//            itemView.findViewById(R.id.addcart).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Log.d("Activity", "Kosárhoz adás");
//                }
//            });
//
//        }
//
//        public void bindTo(Shoppingitem currentItem) {
//            mtitletex.setText(currentItem.getName());
//            minfotext.setText(currentItem.getInfo());
//            mpricetext.setText(currentItem.getPrice());
//            mratingbar.setRating(currentItem.getRatedinfo());
//
//            Glide.with(mContext).load(currentItem.getimageresource()).into(mitemimage);
//        }
//    };
//};
//
