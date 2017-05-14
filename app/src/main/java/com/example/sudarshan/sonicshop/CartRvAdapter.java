package com.example.sudarshan.sonicshop;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sudarshan.sonicshop.models.Product;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CartRvAdapter extends RecyclerView.Adapter<CartRvAdapter.ViewHolder> {

    static CartRvAdapter INS;
    private List<Cart> myItems;
    private Context context;
    public double sum;

    public CartRvAdapter(List<Cart> items, Context context) {
        myItems = items;
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cart_card_layout, parent, false));

    }

    @Override
    public int getItemCount() {
        return myItems.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData(myItems.get(position));
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        // TODO - Your view members
        @BindView(R.id.cart_item_image)
        ImageView cartItemImage;
        @BindView(R.id.cart_item_name)
        TextView cartItemName;
        @BindView(R.id.cart_item_price)
        TextView cartItemPrice;
        @BindView(R.id.cart_item_quantity)
        TextView cartItemQuantity;
        public Cart item;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            // TODO instantiate/assign view members
        }

        public void setData(Cart item) {
            this.item = item;
            cartItemName.setText(""+item.getProductName());
            cartItemPrice.setText(""+item.getPrice());
            cartItemQuantity.setText(""+item.getQuantity());
            sum= sum+ (Double.parseDouble(item.getPrice()+"")*item.getQuantity());


//
            Glide.with(context).load(item.getPicurl()).placeholder(R.drawable.ic_basket).into(cartItemImage);
            // TODO set data to view
        }


    }


}
                                