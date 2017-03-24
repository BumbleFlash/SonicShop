package com.example.sudarshan.sonicshop.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.ButtonBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.sudarshan.sonicshop.R;
import com.example.sudarshan.sonicshop.models.Product;
import com.example.sudarshan.sonicshop.users;
import com.firebase.ui.FirebaseUI;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductListFragment extends Fragment {
private FirebaseRecyclerAdapter<Product, ListItemViewHolder> mAdapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<Product> products = new ArrayList<>();
    Button inc,dec,b;
    static int i=0;
    int q;
    TextView quantity;
    DatabaseReference ref,ref2;
        String u;
    users us= new users();
    FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
    public ProductListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_view_layout, container, false);
//        final ListView listView = (ListView)view.findViewById(R.id.products_listview);
         recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);
         layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        String email= user.getEmail();
        String name= user.getDisplayName();

        b= (Button)view.findViewById(R.id.place_order);
        b.setVisibility(view.INVISIBLE);
        ref= FirebaseDatabase.getInstance().getReference();
        ref2= FirebaseDatabase.getInstance().getReference("users");
        u= user.getUid();


//        products.add(new Product("Whatever",999.00,2));
//        products.add(new Product("Whatever1",999.00,2));
//      products.add(new Product("Whateve2",999.00,2));
//        ref.child("items").setValue(products);
//        products.clear();

//        mAdapter= new FirebaseListAdapter<Product>(getActivity(),Product.class,R.layout.list_item,ref.child("items")) {
//
//
//            @Override
//            protected void populateView(View v, Product p, int position) {
//                Log.e("asdasd", "populateView: "+p.getProductName()+p.getPrice());
//                TextView itemNameTV = (TextView)v.findViewById(R.id.item_name);
//                TextView itemPrice = (TextView)v.findViewById(R.id.plist_price_text);
//                ImageView imageView=(ImageView)v.findViewById(R.id.list_image);
//                Glide.with(getActivity()).load(p.getPicurl()).placeholder(R.drawable.ic_basket).into(imageView);
//                itemNameTV.setText(""+p.getProductName());
//                itemPrice.setText(""+p.getPrice());
//            }
//        };


        mAdapter = new FirebaseRecyclerAdapter<Product, ListItemViewHolder>(Product.class,
                   R.layout.list_item, ListItemViewHolder.class, ref.child("items")) {
            @Override
            protected void populateViewHolder(final ListItemViewHolder viewHolder, Product model, final int position) {
              viewHolder.itemNameTV.setText(model.getProductName());
                viewHolder.itemPrice.setText(""+model.getPrice());
                Glide.with(getActivity()).load(model.getPicurl()).placeholder(R.drawable.ic_basket).into(viewHolder.imageView);

                viewHolder.inc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        q= Integer.parseInt(viewHolder.quantity.getText().toString());
                        q++;
                        viewHolder.quantity.setText(""+q);
                    }
                });
                viewHolder.dec.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        q= Integer.parseInt(viewHolder.quantity.getText().toString());
                        if(q!=0&&q==1)
                        q--;
                        else
                        Toast. makeText(getActivity(),"Can't decrease more",Toast.LENGTH_SHORT).show();

                        viewHolder.quantity.setText(""+q);
                    }
                });
                viewHolder.add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String n= viewHolder.itemNameTV.getText().toString();
                        double p= Double.parseDouble(viewHolder.itemPrice.getText().toString());
                        int qt= Integer.parseInt(viewHolder.quantity.getText().toString());
                        us.setUname(n);
                        us.setUprice(p);
                        us.setQuantity(qt);
                        ref2.child(u).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for(DataSnapshot d: dataSnapshot.getChildren())
                                {
                                    i++;
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });

                        ref.child("users").child(u).child(""+i).setValue(us);
                        i++;


                        Toast.makeText(getActivity(),"Added to Cart!",Toast.LENGTH_SHORT).show();
                    }
                });



            }
        };
          recyclerView.setAdapter(mAdapter);

//        listView.setAdapter(mAdapter);

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
    public static class ListItemViewHolder extends RecyclerView.ViewHolder{
        TextView itemNameTV, itemPrice,quantity;
        ImageView imageView;
        Button add,inc,dec;

        public ListItemViewHolder(View itemView) {
            super(itemView);
             itemNameTV = (TextView)itemView.findViewById(R.id.item_name);
             itemPrice = (TextView)itemView.findViewById(R.id.plist_price_text);
            imageView = (ImageView)itemView.findViewById(R.id.list_image);
             add= (Button)itemView.findViewById(R.id.add_to_cart);
            inc= (Button)itemView.findViewById(R.id.inc);
            quantity= (TextView)itemView.findViewById(R.id.quantity);
            dec= (Button)itemView.findViewById(R.id.dec);


        }
    }


}
