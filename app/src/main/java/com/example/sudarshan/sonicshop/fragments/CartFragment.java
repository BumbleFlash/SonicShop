package com.example.sudarshan.sonicshop.fragments;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.sudarshan.sonicshop.AdapterInterface;
import com.example.sudarshan.sonicshop.Cart;
import com.example.sudarshan.sonicshop.CartRvAdapter;
import com.example.sudarshan.sonicshop.Change;
import com.example.sudarshan.sonicshop.LoginActivity;
import com.example.sudarshan.sonicshop.Mail;
import com.example.sudarshan.sonicshop.NavDrawer;
import com.example.sudarshan.sonicshop.OrderSummaryAdapter;
import com.example.sudarshan.sonicshop.R;
import com.example.sudarshan.sonicshop.RetrofitBuilder;
import com.example.sudarshan.sonicshop.RetrofitInterface;
import com.example.sudarshan.sonicshop.adapters.DialogAdapter;
import com.example.sudarshan.sonicshop.models.Product;
import com.example.sudarshan.sonicshop.users;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.gson.Gson;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.R.attr.value;
import static android.R.id.edit;
import static android.R.id.list;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends Fragment implements NavigationView.OnNavigationItemSelectedListener{
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
//    RecyclerView.Adapter adapter;
    CartRvAdapter adapter;
    Change c;
    String email= LoginActivity.getActivityInstance().getData();

    Button b;
    DatabaseReference ref,cref,ccref;
    String userId;
    ListView dialoglistview;
    List<Cart> cr;
    DialogAdapter mDialogAdapter;
    double sum;
    Mail m;
    NavigationView nav;

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
        final AVLoadingIndicatorView progressBar= (AVLoadingIndicatorView)rootView.findViewById(R.id.avi);
        progressBar.show();
         b= (Button)rootView.findViewById(R.id.place_order);


        dialoglistview= (ListView)rootView.findViewById(R.id.dialog_rec_view);
       // mDialogAdapter = new DialogAdapter(getActivity(), null);


//        ref.child(userId).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for(DataSnapshot d: dataSnapshot.getChildren())
//                {   Cart c=d.getValue(Cart.class);
//                    carts.add(c);
//                    sum= sum+ (Double.parseDouble(c.getUprice()+"")*c.getQuantity());
//                    Log.d("sum",""+sum);
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
        ;

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog;
                dialog = new Dialog(getActivity());


                LinearLayoutManager mLayoutManager;

                OrderSummaryAdapter orderadapter = new OrderSummaryAdapter(cr,getContext());

                Log.d("Sum",sum+"");
                dialog.setContentView(R.layout.activity_ordersummary);
                RecyclerView rv=(RecyclerView)dialog.findViewById(R.id.dialog_rec_view);
                mLayoutManager = new LinearLayoutManager(getActivity());
                Button confirm=(Button)dialog.findViewById(R.id.ok);
                confirm.setText("Pay(₹"+ sum+")");
                rv.setHasFixedSize(true);
                rv.setLayoutManager(mLayoutManager);
                rv.setAdapter(orderadapter);

                c=new Change("",0,3,email);
                Button cancel=(Button)dialog.findViewById(R.id.cancel);
                confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RetrofitInterface client= RetrofitBuilder.createService(RetrofitInterface.class);
                        Call<Void> call=client.sendUpdate(new Gson().toJson(c, Change.class));
                        call.enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                FragmentManager f=  getActivity().getSupportFragmentManager();
                                f.beginTransaction().replace(R.id.content_frame,new CartFragment()).commit();
                                dialog.dismiss();
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {

                            }
                        });
//                        m= new Mail("suddileo@gmail.com","pammas22");
//                        String[] to= {FirebaseAuth.getInstance().getCurrentUser().getEmail()};
//                        m.set_to(to);
//                        m.set_from("suddileo@gmail.com");
//                        m.set_subject("Your order summary ");
//                        StringBuffer body=new StringBuffer();
//                        for(Cart ct: cr)
//                        {
//                          body.append("\n" + ct.getProductName()+" x"+ct.getQuantity()+" "+ (Double.parseDouble(ct.getPrice()+"")*ct.getQuantity()));
//                        }
//                        body.append("\nTotal Price: "+sum);
//                        dialog.dismiss();
//                        m.set_body(""+body);
//
//                        try {
//
//
//                            if (m.send()) {
//                                //dialog.dismiss();
//                                Toast.makeText(getActivity(), "Thank you for ordering from sonic shop", Toast.LENGTH_LONG).show();
//                                //System.exit(0);
//                                ref.child(userId).setValue(null);
//
//
//
//
//                                //Snackbar.make(findViewById(android.R.id.content), "Booking Confirmed!", Snackbar.LENGTH_SHORT).show();
//                            } else {
//                                // dialog.dismiss();
//                                Toast.makeText(getActivity(), "Failed to send Prescription !", Toast.LENGTH_LONG).show();
//
//                                //Snackbar.make(findViewById(android.R.id.content), "Booking not confirmed!", Snackbar.LENGTH_SHORT).show();
//                            }
//
//                        } catch (Exception e) {
//                            //Toast.makeText(MailApp.this, "There was a problem sending the email.", Toast.LENGTH_LONG).show();
//                            Log.e("MailApp", "Could not send email", e);
//                        }
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




String email= LoginActivity.getActivityInstance().getData();
 RetrofitInterface client = RetrofitBuilder.createService(RetrofitInterface.class);
        Call<List<Cart>> call= client.getCart(email);
        call.enqueue(new Callback<List<Cart>>() {
            @Override
            public void onResponse(Call<List<Cart>> call, Response<List<Cart>> response) {
                cr= response.body();

                adapter= new CartRvAdapter(cr, getContext(), new AdapterInterface() {
                    @Override
                    public void pass(double value) {
                        sum= value;
                    }
                });
                progressBar.hide();
                recyclerView.setAdapter(adapter);
                for(int i=0;i<cr.size();i++)
                {
                    Log.v("List",cr.get(i)+"");
                }

            }

            @Override
            public void onFailure(Call<List<Cart>> call, Throwable t) {

            }
        });




//        adapter= new FirebaseRecyclerAdapter<Cart, Cartlistholder>(Cart.class,R.layout.cart_card_layout,Cartlistholder.class,ref.child(userId)) {
//            @Override
//            protected void populateViewHolder(final Cartlistholder viewHolder, final Cart model, int position) {
//
//                viewHolder.cartviewname.setText("" + model.getUname());
//                viewHolder.cartviewprice.setText("Price: " + model.getUprice());
//                viewHolder.cartviewquantity.setText("Quantity: " + model.getQuantity());
//                Glide.with(getActivity()).load(model.getPic()).placeholder(R.drawable.ic_basket).into(viewHolder.img);
//                ref.addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        progressBar.hide();
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//
//                    }
//                });
//
//
//            }
//
//        };
        ;



        return rootView;


        // Inflate the layout for this fragment
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }


    public static class Cartlistholder extends RecyclerView.ViewHolder
{
    TextView cartviewname,cartviewprice,cartviewquantity;
ImageView img;

    public Cartlistholder(View itemView) {
        super(itemView);
        cartviewname= (TextView)itemView.findViewById(R.id.cart_item_name);
        cartviewprice=(TextView)itemView.findViewById(R.id.cart_item_price);
        cartviewquantity=(TextView)itemView.findViewById(R.id.cart_item_quantity);
        img= (ImageView)itemView.findViewById(R.id.cart_item_image);

    }

}

}
