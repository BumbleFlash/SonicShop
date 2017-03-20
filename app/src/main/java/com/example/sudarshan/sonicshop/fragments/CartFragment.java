package com.example.sudarshan.sonicshop.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sudarshan.sonicshop.R;
import com.example.sudarshan.sonicshop.adapters.CartAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;


    public CartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recycler_view_layout, container, false);
        recyclerView = (RecyclerView)rootView.findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new CartAdapter();
        recyclerView.setAdapter(adapter);
        return rootView;


        // Inflate the layout for this fragment
    }

}
