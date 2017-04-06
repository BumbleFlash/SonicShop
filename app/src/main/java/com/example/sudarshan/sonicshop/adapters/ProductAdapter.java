package com.example.sudarshan.sonicshop.adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.sudarshan.sonicshop.R;
import com.example.sudarshan.sonicshop.models.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 3/18/2017.
 */

public class ProductAdapter extends ArrayAdapter<Product> {

    public ProductAdapter(Activity context, List<Product> products){
        super(context,0, products);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView==null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent,false);
        }
        Product product = getItem(position);
        TextView itemNameTV = (TextView)listItemView.findViewById(R.id.item_name);
        TextView itemPrice = (TextView)listItemView.findViewById(R.id.plist_price_text);

//        itemNameTV.setText(""+product.getProductName());
//    itemPrice.setText(""+product.getProductPrice());
//
return listItemView;


    }
}
