package com.example.sudarshan.sonicshop.fragments;


import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sudarshan.sonicshop.Cart;
import com.example.sudarshan.sonicshop.OrderSummaryAdapter;
import com.example.sudarshan.sonicshop.R;
import com.example.sudarshan.sonicshop.adapters.DialogAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

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
    Button b;
    DatabaseReference ref,cref,ccref;
    String userId;
    ListView dialoglistview;
    ArrayList<Cart> carts=new ArrayList<>();
    DialogAdapter mDialogAdapter;
    double sum;
    public CartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.recycler_view_layout, container, false);
        recyclerView = (RecyclerView)rootView.findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

         b= (Button)rootView.findViewById(R.id.place_order);

        dialoglistview= (ListView)rootView.findViewById(R.id.dialog_rec_view);
       // mDialogAdapter = new DialogAdapter(getActivity(), null);
     ref= FirebaseDatabase.getInstance().getReference("users");
       userId =user.getUid();
        ref.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot d: dataSnapshot.getChildren())
                {   Cart c=d.getValue(Cart.class);
                    carts.add(c);
                    sum= sum+ (c.getUprice()*c.getQuantity());
                    Log.d("sum",""+sum);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog;

                LinearLayoutManager mLayoutManager;
                dialog = new Dialog(getActivity());
                OrderSummaryAdapter o=new OrderSummaryAdapter(carts);
                dialog.setContentView(R.layout.activity_ordersummary);
                RecyclerView rv=(RecyclerView)dialog.findViewById(R.id.dialog_rec_view);
                mLayoutManager = new LinearLayoutManager(getActivity());
                rv.setHasFixedSize(true);
                rv.setLayoutManager(mLayoutManager);
                rv.setAdapter(o);
                Button confirm=(Button)dialog.findViewById(R.id.ok);
                confirm.setText("Pay(₹"+ sum+")");
                Button cancel=(Button)dialog.findViewById(R.id.cancel);
                confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            dialog.show();
            }
        });




        adapter= new FirebaseRecyclerAdapter<Cart, Cartlistholder>(Cart.class,R.layout.cart_card_layout,Cartlistholder.class,ref.child(userId)) {
            @Override
            protected void populateViewHolder(final Cartlistholder viewHolder, final Cart model, int position) {

                viewHolder.cartviewname.setText("" + model.getUname());
                viewHolder.cartviewprice.setText("Price: " + model.getUprice());
                viewHolder.cartviewquantity.setText("Quantity: " + model.getQuantity());

            }

        };
        recyclerView.setAdapter(adapter);



        return rootView;


        // Inflate the layout for this fragment
    }
public static class Cartlistholder extends RecyclerView.ViewHolder
{
    TextView cartviewname,cartviewprice,cartviewquantity;


    public Cartlistholder(View itemView) {
        super(itemView);
        cartviewname= (TextView)itemView.findViewById(R.id.cart_item_name);
        cartviewprice=(TextView)itemView.findViewById(R.id.cart_item_price);
        cartviewquantity=(TextView)itemView.findViewById(R.id.cart_item_quantity);

    }

}

}
