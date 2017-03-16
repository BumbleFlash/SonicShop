package com.example.sudarshan.sonicshop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<itemClass> items;
    private itemsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        items = new ArrayList<>();
        items.add(new itemClass("AMD Radeon R5 M330",6000 ));
        items.add(new itemClass("Nivia football", 500 ));
        items.add(new itemClass("Nike studs", 3500 ));
        items.add(new itemClass("Sony Playstation 3",22000 ));
        items.add(new itemClass("xdsfd",324 ));
        items.add(new itemClass("dfsd",343 ));
        items.add(new itemClass("fessd",60000 ));
        items.add(new itemClass("AMD Radeon R5 M330",6000 ));
        items.add(new itemClass("AMD Radeon R5 M330",6000 ));
        items.add(new itemClass("AMD Radeon R5 M330",6000 ));
        mAdapter = new itemsAdapter(this, items);
        ListView listView = (ListView)findViewById(R.id.list_view);
        listView.setAdapter(mAdapter);











    }

}
