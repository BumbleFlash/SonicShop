package com.example.sudarshan.sonicshop.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.sudarshan.sonicshop.R;
import com.example.sudarshan.sonicshop.Cart;
import com.example.sudarshan.sonicshop.users;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.R.id.list;
import static java.security.AccessController.getContext;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
//    RecyclerView.Adapter adapter;
    FirebaseRecyclerAdapter<Cart,Cartlistholder> adapter;
DataSnapshot dataSnapshot;
    FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();

    DatabaseReference ref,cref,ccref;
    String userId;

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
     ref= FirebaseDatabase.getInstance().getReference("users");
       userId =user.getUid();




        adapter= new FirebaseRecyclerAdapter<Cart, Cartlistholder>(Cart.class,R.layout.cart_card_layout,Cartlistholder.class,ref.child(userId)) {
            @Override
            protected void populateViewHolder(final Cartlistholder viewHolder, final Cart model, int position) {

                viewHolder.cartviewname.setText("" + model.getUname());
                viewHolder.cartviewprice.setText("" + model.getUprice());




            }


        };
        recyclerView.setAdapter(adapter);



        return rootView;


        // Inflate the layout for this fragment
    }
public static class Cartlistholder extends RecyclerView.ViewHolder
{
    TextView cartviewname,cartviewprice;
    Button b;

    public Cartlistholder(View itemView) {
        super(itemView);
        cartviewname= (TextView)itemView.findViewById(R.id.cart_item_name);
        cartviewprice=(TextView)itemView.findViewById(R.id.cart_item_price);
    }

}

}
