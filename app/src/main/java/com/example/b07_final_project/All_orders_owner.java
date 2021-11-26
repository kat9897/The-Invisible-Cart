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
    private FirebaseDatabase database;
    private DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_orders_owner);

        //TODO adding a Back button to Store Owner's Home
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Get Owner ID
        // We need owner id to check the orders of that particular owner
        Intent intent = getIntent();
        // owner id is stored in a string uid
        String uid = intent.getExtras().getString("Ownerid");


        // Initializing ListView
        listview_all_orders = (ListView) findViewById(R.id.listview_orders);
        // we are going to create an arraylist of all the order IDs and insert them in ListView
        ArrayList<Integer> orderIdList = new ArrayList<>();

        //TODO  -->
        // go to database -> Users -> Owner -> Email of Owner / uid -> List of Orders -> Order ID
        // get the order IDs and save them in arraylist
        // then present these order IDs to ListView on the app

        // Since our data is in the Firebase Database
        database = FirebaseDatabase.getInstance();
        // Reference to List of Orders of Owner
        ref = database.getReference("Users").child(uid).child(uid).child("Orders");
        ref.addValueEventListener(new com.google.firebase.database.ValueEventListener() {

            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    // get the order ID of each order
                    int order_id = postSnapshot.child("orderId").getValue(int.class);

                    // store order ID to the array list of ID
                    orderIdList.add(order_id);
                }

                // convert the arraylist to arrayadapter
                ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1, orderIdList);
                // set arrayadapter to listview
                listview_all_orders.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        }
    }
}