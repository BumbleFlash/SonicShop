package com.example.sudarshan.sonicshop.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sudarshan.sonicshop.R;

/**
 * Created by admin on 3/19/2017.
 */

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private String[] itemName= {"Pineapple", "Apple", "Orange", "Strawberry"};
    private String[] itemPrice = {"350","234","324","343"};

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView itemNameTV, itemPriceTV;
        public ViewHolder(View itemView){
            super(itemView);
            itemNameTV = (TextView)itemView.findViewById(R.id.cart_item_name);
            itemPriceTV = (TextView)itemView.findViewById(R.id.cart_item_price);
        }
    }
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cart_card_layout, parent, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(CartAdapter.ViewHolder holder, int position) {
        holder.itemNameTV.setText("Item name:  " + itemName[position]);
        holder.itemPriceTV.setText("Item Price: " + itemPrice[position]);

    }

    @Override
    public int getItemCount() {
        return itemName.length;
    }
}
