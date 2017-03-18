package com.example.sudarshan.sonicshop.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.sudarshan.sonicshop.R;
import com.example.sudarshan.sonicshop.adapters.ProductAdapter;
import com.example.sudarshan.sonicshop.models.Product;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductListFragment extends Fragment {
private ProductAdapter mAdapter;

    public ProductListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.itemlist, container, false);
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product("Pineapple", 250, 10));
        products.add(new Product("Pineapple", 250, 10));
        products.add(new Product("Pineapple", 250, 10));
        products.add(new Product("Pineapple", 250, 10));
        products.add(new Product("Pineapple", 250, 10));
        products.add(new Product("Pineapple", 250, 10));
        products.add(new Product("Pineapple", 250, 10));
        products.add(new Product("Pineapple", 250, 10));
        mAdapter = new ProductAdapter(getActivity(), products);
        ListView listView = (ListView)view.findViewById(R.id.products_listview);
        listView.setAdapter(mAdapter);



        return view;
    }

}
