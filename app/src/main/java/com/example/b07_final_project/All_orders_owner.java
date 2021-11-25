package com.example.b07_final_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class All_orders_owner extends AppCompatActivity {
    private ListView listview_all_orders;
    private DatabaseReference firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_orders_owner);
        // Hide TitleBar
        getSupportActionBar().hide();
//        Get Owner ID
        Intent intent = getIntent();
        String uid = intent.getExtras().getString("Ownerid");

        listview_all_orders = (ListView) findViewById(R.id.listview_orders);
        ArrayList<String> orders = new ArrayList<>();

        firebaseDatabase = FirebaseDatabase.getInstance().getReference();;
//        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        // TODO  --> Find all values of products

        firebaseDatabase.child("Owner").child(uid).child(uid).child("products").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String order = snapshot.getValue(String.class);
                //for(DataSnapshot child:snapshot.getChildren()) {
                    // Order orders = child.getValue(Order.class)
                    // String name = product.getName();
                    // String brand = product.getBrand();
                    // String price = product.getPrice();
                    //Save all in Array
//            }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
//        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,orders);
//        listview_all_orders.setAdapter(arrayAdapter);

    }
}