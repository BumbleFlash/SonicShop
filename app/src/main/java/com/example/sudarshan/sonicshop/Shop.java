//package com.example.sudarshan.sonicshop;
//
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.util.Log;
//import android.widget.ArrayAdapter;
//import android.widget.ListView;
//
//
//import com.google.firebase.database.ChildEventListener;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//import java.util.ArrayList;
//import java.util.Map;
//
//import static android.R.attr.addPrintersActivity;
//import static android.R.attr.value;
//
//public class Shop extends AppCompatActivity {
//    private ArrayList<itemClass> items;
//    private itemsAdapter mAdapter;
//    private ListView listView;
//    itemClass val;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_shop);
//        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
//
//        listView= (ListView)findViewById(R.id.list_view);
//        items= new ArrayList<>();
//        mAdapter= new itemsAdapter(this,items);
//        listView.setAdapter(mAdapter);
//
//        ref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                if (dataSnapshot.exists()) {
//
//                        val = dataSnapshot.getValue(itemClass.class);
//
//                        items.add(val);
//                        mAdapter.notifyDataSetChanged();
//                    }
//
//            else
//                    Log.d("Message", "No Children");
//
//
//                }
//
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//
////        ref.addChildEventListener(new ChildEventListener() {
////            @Override
////            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//////                itemClass val= new itemClass();
////                val= dataSnapshot.getValue(itemClass.class);
////
////                items.add(val);
////                mAdapter.notifyDataSetChanged();
////            }
////
////            @Override
////            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
////
////            }
////
////            @Override
////            public void onChildRemoved(DataSnapshot dataSnapshot) {
////
////            }
////
////            @Override
////            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
////
////            }
////
////            @Override
////            public void onCancelled(DatabaseError databaseError) {
////
////            }
////        });
//
//
//
//    }
//}
