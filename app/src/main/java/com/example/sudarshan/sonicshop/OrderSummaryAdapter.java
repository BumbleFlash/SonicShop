package com.example.sudarshan.sonicshop;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.sudarshan.sonicshop.fragments.CartFragment;
import com.example.sudarshan.sonicshop.models.Product;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class OrderSummaryAdapter extends RecyclerView.Adapter<OrderSummaryAdapter.ViewHolder> {

    private List<Cart> myItems;
    public double sum;

    Context context;


    public OrderSummaryAdapter(List<Cart> items,Context context) {
        myItems = items;

        this.context=context;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_dialog_layout, parent, false)); // TODO
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

        @BindView(R.id.order_name)
        TextView orderName;
        @BindView(R.id.order_price)
        TextView orderPrice;
        // TODO - Your view members
        public Cart item;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            Intent intent= new Intent(context,CartFragment.class);
            intent.putExtra("Sum",sum);
            // TODO instantiate/assign view members
        }


        public void setData(Cart item) {
            this.item = item;
            orderName.setText(item.getProductName() + " x" + item.getQuantity());
            orderPrice.setText(String.valueOf(Double.parseDouble(item.getPrice()) * item.getQuantity()));



            // TODO set data to view
        }


    }

}
