package com.example.sudarshan.sonicshop;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class OrderSummaryAdapter extends RecyclerView.Adapter<OrderSummaryAdapter.ViewHolder> {

    private List<Cart> myItems;


    public OrderSummaryAdapter(List<Cart> items) {
        myItems = items;

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

    public interface ItemListener {
        void onItemClick(Cart item);
    }

    static

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.order_name)
        TextView orderName;
        @BindView(R.id.order_price)
        TextView orderPrice;
        // TODO - Your view members
        public Cart item;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(this);
            // TODO instantiate/assign view members
        }

        public void setData(Cart item) {
            this.item = item;
            orderName.setText(item.getUname()+" x"+ item.getQuantity());
            orderPrice.setText(String.valueOf(item.getUprice()*item.getQuantity()));
            // TODO set data to view
        }

        @Override
        public void onClick(View v) {

        }
    }


}
                                