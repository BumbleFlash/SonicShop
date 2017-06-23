package com.example.sudarshan.sonicshop;

import android.app.Dialog;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.sudarshan.sonicshop.fragments.CartFragment;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.sudarshan.sonicshop.R.id.cart_item_price;
import static com.example.sudarshan.sonicshop.R.id.delete;
import static com.example.sudarshan.sonicshop.R.id.quantity;
import static com.facebook.FacebookSdk.getApplicationContext;


public class CartRvAdapter extends RecyclerView.Adapter<CartRvAdapter.ViewHolder> {

    static CartRvAdapter INS;
    int choice =0;

String email= LoginActivity.getActivityInstance().getData();
    private List<Cart> myItems;
    private Context context;
    public double sum;
    TextView quantity,itemName;
    AdapterInterface adapterInterface;

    public CartRvAdapter(List<Cart> items, Context context, AdapterInterface adapterInterface) {
        myItems = items;
        this.context = context;
        this.adapterInterface = adapterInterface;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cart_card_layout, parent, false));

    }

    @Override
    public int getItemCount() {
        return myItems.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData(myItems.get(position));
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        // TODO - Your view members
        @BindView(R.id.cart_item_image)
        ImageView cartItemImage;
        @BindView(R.id.cart_item_name)
        TextView cartItemName;
        @BindView(R.id.cart_item_price)
        TextView cartItemPrice;
        @BindView(R.id.cart_item_quantity)
        TextView cartItemQuantity;
        @BindView(R.id.update)
        Button edit;
        @BindView(R.id.delete)
        Button delete;
        public Cart item;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            // TODO instantiate/assign view members
        }

        public void setData(final Cart item) {
            this.item = item;
            cartItemName.setText("Item Name:  " + item.getProductName());
            cartItemPrice.setText("Item Price:   " + item.getPrice());
            cartItemQuantity.setText("Quantity:    " + item.getQuantity());
            sum = sum + (Double.parseDouble(item.getPrice() + "") * item.getQuantity());
            adapterInterface.pass(sum);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    choice= 2;
                    Change c= new Change(item.getProductName()+"",item.getQuantity(),choice,email);
                    RetrofitInterface client= RetrofitBuilder.createService(RetrofitInterface.class);
                    Call<Void> call= client.sendUpdate(new Gson().toJson(c,Change.class));
                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {

                            FragmentManager f= ((NavDrawer) context).getSupportFragmentManager();
                            f.beginTransaction().replace(R.id.content_frame,  new CartFragment()).commit();
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {

                        }
                    });
                }
            });
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Dialog dialog= new Dialog(context);
                    LinearLayoutManager layoutManager;
                    layoutManager= new LinearLayoutManager(context);

                    dialog.setContentView(R.layout.edit_dialog);
                    dialog.show();
                    Button update= (Button)dialog.findViewById(R.id.update);
                    Button cancel= (Button)dialog.findViewById(R.id.cancel);
                    Button inc= (Button)dialog.findViewById(R.id.inc);
                    Button dec= (Button)dialog.findViewById(R.id.dec);
                    quantity= (TextView)dialog.findViewById(R.id.quantity);
                    itemName= (TextView)dialog.findViewById(R.id.item_name);
                    TextView itemPrice= (TextView)dialog.findViewById(R.id.plist_price_text);
                    itemName.setText(item.getProductName()+"");
                    itemPrice.setText(item.getPrice()+"");
                    quantity.setText(item.getQuantity()+"");
                    cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                    inc.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int i= Integer.parseInt(quantity.getText().toString());
                            i++;
                            quantity.setText(i+"");
                        }
                    });
                    dec.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int j= Integer.parseInt(quantity.getText().toString());
                            if(j==1)
                            {
                                Toast.makeText(getApplicationContext(),"Can't decrease more",Toast.LENGTH_SHORT).show();

                            }
                            else {
                                j--;
                                quantity.setText(j + "");
                            }
                        }
                    });

                    update.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            choice=1;
                            Change c= new Change(itemName.getText()+"",Integer.parseInt(quantity.getText().toString()),choice,email);
                            RetrofitInterface client= RetrofitBuilder.createService(RetrofitInterface.class);
                            Call<Void> call= client.sendUpdate(new Gson().toJson(c,Change.class));
                            call.enqueue(new Callback<Void>() {
                                @Override
                                public void onResponse(Call<Void> call, Response<Void> response) {
                                    dialog.dismiss();
                                    FragmentManager f= ((NavDrawer) context).getSupportFragmentManager();
                                    f.beginTransaction().replace(R.id.content_frame,  new CartFragment()).commit();
                                }

                                @Override
                                public void onFailure(Call<Void> call, Throwable t) {

                                }
                            });

                        }
                    });


                }
            });


//
            Glide.with(context).load(item.getPicurl()).placeholder(R.drawable.ic_basket).into(cartItemImage);
            // TODO set data to view
        }


    }


}
                                