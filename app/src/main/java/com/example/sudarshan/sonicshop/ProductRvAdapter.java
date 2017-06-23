package com.example.sudarshan.sonicshop;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.sudarshan.sonicshop.models.Product;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.facebook.FacebookSdk.getApplicationContext;
import static java.security.AccessController.getContext;


public class ProductRvAdapter extends RecyclerView.Adapter<ProductRvAdapter.ViewHolder> {

    private List<Product> myItems;
    private Context mContext;
//    private CartRvAdapter adapter;
//    private  RecyclerView recyclerView;
    String email;

    public ProductRvAdapter(List<Product> myItems, Context mContext) {
        this.myItems = myItems;
        this.mContext = mContext;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false)); // TODO
    }

    @Override
    public int getItemCount() {
        return myItems.size();
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.setData(myItems.get(position), mContext);
holder.inc.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        int i= Integer.parseInt(holder.quantity.getText().toString());
        i++;
        holder.quantity.setText(i+"");
    }
});
        holder.dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int j= Integer.parseInt(holder.quantity.getText().toString());
                if(j==1)
                {
                    Toast.makeText(getApplicationContext(),"Can't decrease more",Toast.LENGTH_SHORT).show();

                }
                else {
                    j--;
                    holder.quantity.setText(j + "");
                }
            }
        });
        holder.addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name= holder.itemName.getText().toString();
                double price= Double.parseDouble(holder.plistPriceText.getText().toString().substring(3));


                int q= Integer.parseInt(holder.quantity.getText()+"");
                email= LoginActivity.getActivityInstance().getData();
                Log.e("email",email);
                Cart c= new Cart();
                c.setProductName(name);
                c.setPrice(price);
                c.setQuantity(q);
                c.setUemail(email);

                RetrofitInterface client= RetrofitBuilder.createService(RetrofitInterface.class);
                Call<Void> call = client.setProducts(new Gson().toJson(c,Cart.class));
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                       Toast.makeText(getApplicationContext(),"Added to Cart",Toast.LENGTH_SHORT).show();


                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });

            }
        });

    }



    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.list_image)
        ImageView listImage;
        @BindView(R.id.thumbnail)
        LinearLayout thumbnail;
        @BindView(R.id.item_name)
        TextView itemName;
        @BindView(R.id.plist_price_text)
        TextView plistPriceText;
        @BindView(R.id.dec)
        Button dec;
        @BindView(R.id.quantity)
        TextView quantity;
        @BindView(R.id.inc)
        Button inc;
        @BindView(R.id.add_to_cart)
        Button addToCart;


        // TODO - Your view members
        public Product item;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
//            itemView.setOnClickListener(this);
            // TODO instantiate/assign view members
        }

        public void setData(Product item,Context mcont) {
            this.item = item;
            itemName.setText(item.getProductName());
            plistPriceText.setText("Rs."+item.getPrice());
            Glide.with(mcont).load(item.getPicurl()).placeholder(R.drawable.ic_basket).into(listImage);


            // TODO set data to view
        }

    }

}
                                