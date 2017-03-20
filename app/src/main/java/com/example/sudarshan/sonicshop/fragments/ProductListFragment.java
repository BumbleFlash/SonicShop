package com.example.sudarshan.sonicshop.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sudarshan.sonicshop.Itemclass;
import com.example.sudarshan.sonicshop.R;
import com.example.sudarshan.sonicshop.adapters.ProductAdapter;
import com.example.sudarshan.sonicshop.models.Product;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductListFragment extends Fragment {
private FirebaseListAdapter<Product> mAdapter;
    ArrayList<Product> products = new ArrayList<>();
    public ProductListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.itemlist, container, false);
        final ListView listView = (ListView)view.findViewById(R.id.products_listview);
        DatabaseReference ref= FirebaseDatabase.getInstance().getReference();

//        products.add(new Product("Whatever",999.00,2));
//        products.add(new Product("Whatever1",999.00,2));
//      products.add(new Product("Whateve2",999.00,2));
//        ref.child("items").setValue(products);
//        products.clear();

        mAdapter= new FirebaseListAdapter<Product>(getActivity(),Product.class,R.layout.list_item,ref.child("items")) {


            @Override
            protected void populateView(View v, Product p, int position) {
                Log.e("asdasd", "populateView: "+p.getProductName()+p.getPrice());
                TextView itemNameTV = (TextView)v.findViewById(R.id.item_name);
                TextView itemPrice = (TextView)v.findViewById(R.id.plist_price_text);
                ImageView imageView=(ImageView)v.findViewById(R.id.list_image);
                Glide.with(getActivity()).load(p.getPicurl()).placeholder(R.drawable.ic_basket).into(imageView);
                itemNameTV.setText(""+p.getProductName());
                itemPrice.setText(""+p.getPrice());
            }
        };
        listView.setAdapter(mAdapter);

        //listView.setAdapter(mAdapter);
//        ref.child("items").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
//                    Product p = postSnapshot.getValue(Product.class);
//                    if (!products.contains(p) && products != null)
//                        products.add(p);
//
//                }
//                mAdapter = new ProductAdapter(getActivity(), products);
//                listView.setAdapter(mAdapter);
//
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//        ref.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s){
//
//               Product val= dataSnapshot.getValue(Product.class);
//                products.add(val);
//                mAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
////        });
//        products.add(new Product("Pineapple", 250, 10));
//        products.add(new Product("Pineapple", 250, 10));
//        products.add(new Product("Pineapple", 250, 10));
//        products.add(new Product("Pineapple", 250, 10));
//        products.add(new Product("Pineapple", 250, 10));
//        products.add(new Product("Pineapple", 250, 10));
//        products.add(new Product("Pineapple", 250, 10));
//        products.add(new Product("Pineapple", 250, 10));




        return view;
    }

}
