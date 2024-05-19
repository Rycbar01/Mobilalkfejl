package com.example.konyvwebshop;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ShoppingitemAdapter extends RecyclerView.Adapter<ShoppingitemAdapter.ViewHolder> implements Filterable{

    private static final String TAG = ShoppingitemAdapter.class.getName(); //MainActivity-ről Listre változtattam
    private ArrayList<Shoppingitem> mshoppingitemsdata;
    private ArrayList<Shoppingitem> mshoppingitemsdataAll;
    private Context mcontext;
    private int lastposition = -1;

    ShoppingitemAdapter(Context context, ArrayList<Shoppingitem> itemsdata){
        this.mshoppingitemsdata = itemsdata;
        this.mshoppingitemsdataAll = itemsdata;
        this.mcontext = context;
    }

    @Override
    public ShoppingitemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mcontext).inflate(R.layout.lista_elemek, parent, false));
    }

    @Override
    public void onBindViewHolder(ShoppingitemAdapter.ViewHolder holder, int position) {
        Shoppingitem currentitem =  mshoppingitemsdata.get(position);
        holder.bindTo(currentitem);


        if(holder.getAdapterPosition()>lastposition){
            Animation animation= AnimationUtils.loadAnimation(mcontext, R.anim.sliderow);
            holder.itemView.startAnimation(animation);

            lastposition=holder.getAdapterPosition();
        }
    }

    @Override
    public int getItemCount() { return mshoppingitemsdata.size(); }

    @Override
    public Filter getFilter() { return shoppingFilter; }
    private Filter shoppingFilter = new Filter(){

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            ArrayList<Shoppingitem> filteredList = new ArrayList<>();
            FilterResults results = new FilterResults();

            if(charSequence == null || charSequence.length() == 0){
                results.count = mshoppingitemsdataAll.size();
                results.values = mshoppingitemsdataAll;
            } else {
                String filterPattern = charSequence.toString().toLowerCase().trim();

                for(Shoppingitem item : mshoppingitemsdataAll){
                    if(item.getName().toLowerCase().contains(filterPattern)){
                        filteredList.add(item);
                    }
                }

                results.count = filteredList.size();
                results.values = filteredList;
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults results) {
            mshoppingitemsdata = (ArrayList) results.values; //vidiben filterResults.values volt
            notifyDataSetChanged();
        }
    };


    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mtitletext;
        private TextView minfotext;
        private TextView mpricetext;
        private TextView mcounttext;
        private ImageView mitemimage;
        private RatingBar mratingbar;

        public ViewHolder(View itemView) {
            super(itemView);
            mtitletext=itemView.findViewById(R.id.itemtitle);
            minfotext=itemView.findViewById(R.id.subtitle);
            mpricetext=itemView.findViewById(R.id.price);
            mcounttext=itemView.findViewById(R.id.count);
            mitemimage=itemView.findViewById(R.id.itemimage);
            mratingbar=itemView.findViewById(R.id.ratingbar);
            itemView.findViewById(R.id.addcart).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("Tevékenység", "Kosárhoz adás");
                   //((ShoplistActivity)mcontext).updateAlertIcon();
                }
            });
        }
        public void bindTo(Shoppingitem currentitem) {

            mtitletext.setText(currentitem.getName());
            minfotext.setText(currentitem.getInfo());
            mpricetext.setText(currentitem.getPrice());
            mcounttext.setText(String.valueOf(currentitem.getCount()));
            mratingbar.setRating(currentitem.getRatedinfo());



            Glide.with(mcontext)
                    .load(currentitem.getImageresource())
                    .into(mitemimage);


            itemView.findViewById(R.id.addcart).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Rendelés gomb megnyomva");
                    ((ListActivity)mcontext ).updateCount(currentitem);
                }
            });

            itemView.findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Törlés gomb megnyomva");
                    ((ListActivity)mcontext).delete(currentitem);
                }
            });

        }
    }
}
















