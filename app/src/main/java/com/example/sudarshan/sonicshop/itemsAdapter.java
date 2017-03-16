package com.example.sudarshan.sonicshop;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;



public class itemsAdapter extends ArrayAdapter<itemClass> {
    public itemsAdapter(Activity context, ArrayList<itemClass> items){
        super(context, 0, items);

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView==null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        itemClass item = getItem(position);
        TextView itemTV = (TextView)listItemView.findViewById(R.id.item_name);
        TextView priceTV = (TextView)listItemView.findViewById(R.id.item_price);
        itemTV.setText(item.getName());
        priceTV.setText(""+ item.getPrice());
        return listItemView;
    }
}
