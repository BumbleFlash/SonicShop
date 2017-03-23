package com.example.sudarshan.sonicshop.adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.sudarshan.sonicshop.Cart;
import com.example.sudarshan.sonicshop.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Sudarshan on 23-03-2017.
 */

public class DialogAdapter extends ArrayAdapter<Cart> {
    public DialogAdapter(Activity context, ArrayList<Cart> carts) {
        super(context, 0, carts);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (convertView == null) {
            LayoutInflater.from(getContext()).inflate(R.layout.order_dialog_layout, parent, false);
        }
        Cart cart = getItem(position);
        TextView name = (TextView) listItemView.findViewById(R.id.order_name);
        name.setText("" + cart.getUname() + " x" + cart.getQuantity());
        TextView price = (TextView) listItemView.findViewById(R.id.order_price);
        price.setText("" + cart.getUprice());
        return listItemView;
    }



    }
