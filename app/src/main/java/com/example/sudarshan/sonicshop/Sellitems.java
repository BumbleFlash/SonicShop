package com.example.sudarshan.sonicshop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sudarshan.sonicshop.models.Sell;
import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Sellitems extends AppCompatActivity {

    @BindView(R.id.product_name)
    EditText productName;
    @BindView(R.id.product_price)
    EditText productPrice;
    @BindView(R.id.spinner_cat)
    Spinner spinnerCat;
    @BindView(R.id.btn_login)
    AppCompatButton sellButton;
    ArrayList<String> spinner_list= new ArrayList<>();
    ArrayAdapter<String> spinnerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sellitems);
        ButterKnife.bind(this);
        spinner_list.add("Computer Science");
        spinner_list.add("Biology");
        spinner_list.add("Electrical");
        spinner_list.add("Physics");
        spinnerAdapter= new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,spinner_list);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCat.setAdapter(spinnerAdapter);
        spinnerAdapter.notifyDataSetChanged();
        sellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name= productName.getText().toString();
                double price=Double.parseDouble(productPrice.getText().toString());
                String cat= spinnerCat.getSelectedItem().toString();
                String email= LoginActivity.getActivityInstance().getData();
                Sell s= new Sell(name,price,cat,email);
                RetrofitInterface client= RetrofitBuilder.createService(RetrofitInterface.class);
                Call<Void> call= client.sendItem(new Gson().toJson(s,Sell.class));
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        startActivity(new Intent(getApplicationContext(),NavDrawer.class));

                        Toast.makeText(getApplicationContext(),"Succesfully Added",Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"Please Check your internet connection or try again later",Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }
}
