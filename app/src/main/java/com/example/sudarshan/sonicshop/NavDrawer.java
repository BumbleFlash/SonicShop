package com.example.sudarshan.sonicshop;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sudarshan.sonicshop.fragments.CartFragment;
import com.example.sudarshan.sonicshop.fragments.ProductListFragment;

public class NavDrawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
TextView mail,n,em,nm;
    ImageView img;
  //  FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
    NavigationView nav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Sonic Shop");
//        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
//        String email= user.getEmail();
//        String usname= user.getDisplayName();
//        mail =(TextView)findViewById(R.id.textView);
//        n=(TextView)findViewById(R.id.textView1);
//        Log.d("Mail", mail+" "+n);
//        mail.setText(email);
//        n.setText(usname);
        nav= (NavigationView) findViewById(R.id.nav_view);
        onNavigationItemSelected(nav.getMenu().findItem(R.id.nav_products));
        onNavigationItemSelected(nav.getMenu().getItem(0));
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Sellitems.class));
            }
        });
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_products) {
            setTitle("Products");

            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction().replace(R.id.content_frame, new ProductListFragment()).commit();
        } else if (id == R.id.nav_cart) {
            setTitle("Cart");
            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction().replace(R.id.content_frame, new CartFragment()).commit();
        }
         else if (id == R.id.nav_order_history) {
            setTitle("Order History");
            FragmentManager fm = getSupportFragmentManager();
//            fm.beginTransaction().replace(R.id.content_frame, new Orderhistory()).commit();
        }
//
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }
        View header=nav.getHeaderView(0);
        String m= LoginActivity.getActivityInstance().getData();
        em= (TextView)header.findViewById(R.id.text_email);
        nm= (TextView)header.findViewById(R.id.text_name);
        img= (ImageView)header.findViewById(R.id.display_photo);
        em.setText(m);
      //  String email= user.getEmail();
        //String name= user.getDisplayName();
        //em.setText(""+ email);
        //nm.setText(""+name);
        //img.setImageURI(user.getPhotoUrl());
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
