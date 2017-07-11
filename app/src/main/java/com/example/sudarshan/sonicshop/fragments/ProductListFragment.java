package com.example.sudarshan.sonicshop.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sudarshan.sonicshop.NavDrawer;
import com.example.sudarshan.sonicshop.ProductRvAdapter;
import com.example.sudarshan.sonicshop.R;
import com.example.sudarshan.sonicshop.RetrofitBuilder;
import com.example.sudarshan.sonicshop.RetrofitInterface;
import com.example.sudarshan.sonicshop.Sellitems;
import com.example.sudarshan.sonicshop.adapters.ProductAdapter;
import com.example.sudarshan.sonicshop.models.Product;
import com.example.sudarshan.sonicshop.users;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductListFragment extends Fragment {
private FirebaseRecyclerAdapter<Product, ListItemViewHolder> mAdapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Toolbar toolbar;
    ArrayList<Product> products = new ArrayList<>();
    Button inc,dec;
    static int i=0;
    int q;
    Button b;
    TextView quantity;
    AVLoadingIndicatorView progressBar;
    DatabaseReference ref,ref2,ref3,ref4;
    ProductRvAdapter productAdapter;

        String u;
    users us= new users();
    //FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
    public ProductListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.spinner_menu_layout,menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id= item.getItemId();

        switch(id)
        {
            case R.id.cat_computer:
                callproduct("Computer Science");
                //Ada(ref4);
//              productAdapter.notifyDataSetChanged();
                break;
            case R.id.cat_biology:
                callproduct("Biology");
                //Ada(ref4);
//                productAdapter.notifyDataSetChanged();
                break;
            case R.id.cat_electrical:
                callproduct("Electrical");
                //Ada(ref4);
//                productAdapter.notifyDataSetChanged();
                break;
            case R.id.cat_physics:
                callproduct("Physics");
                //Ada(ref4);
//                productAdapter.notifyDataSetChanged();
                break;
//kk
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.recycler_view_layout, container, false);
//        final ListView listView = (ListView)view.findViewById(R.id.products_listview);
         recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);
         layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
//        toolbar = (Toolbar)view.findViewById(R.id.toolbar);
//        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_funnel_);
//        toolbar.setOverflowIcon(drawable);
        setHasOptionsMenu(true);
        progressBar = (AVLoadingIndicatorView)view.findViewById(R.id.avi);
        progressBar.show();
        b= (Button)view.findViewById(R.id.place_order);
        b.setVisibility(view.INVISIBLE);
        ref= FirebaseDatabase.getInstance().getReference();
        ref2= FirebaseDatabase.getInstance().getReference("users");
        ref3= FirebaseDatabase.getInstance().getReference("items").child("category");
      //  u= user.getUid();
        callproduct("Computer Science");
        //Ada(ref3.child("computer science"));


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

        ref4= ref3.child("computer science");






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
//            FloatingActionButton fab= (FloatingActionButton)itemView.findViewById(R.id.fab);
//            fab.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                }
//            });


        }
    }

//    public void Ada(final DatabaseReference ref4) {
//        progressBar.show();
//        mAdapter = new FirebaseRecyclerAdapter<Product, ListItemViewHolder>(Product.class,
//                R.layout.list_item, ListItemViewHolder.class, ref4) {
//            @Override
//            protected void populateViewHolder(final ListItemViewHolder viewHolder, final Product model, final int position) {
//                viewHolder.itemNameTV.setText(model.getProductName());
//                viewHolder.itemPrice.setText("" + model.getPrice());
//
//
//            }
//        };
//
//        ref4.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                progressBar.hide();
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//        recyclerView.setAdapter(mAdapter);
//    }
public void callproduct(String s)
{
    RetrofitInterface client= RetrofitBuilder.createService(RetrofitInterface.class);
    Call<List<Product>> call = client.getallproducts(s);
    call.enqueue(new Callback<List<Product>>() {
        @Override
        public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
            List<Product> pr=response.body();
            productAdapter=new ProductRvAdapter(pr,getContext());
            recyclerView.setAdapter(productAdapter);
            progressBar.hide();

        }

        @Override
        public void onFailure(Call<List<Product>> call, Throwable t) {

        }
    });

}
}
