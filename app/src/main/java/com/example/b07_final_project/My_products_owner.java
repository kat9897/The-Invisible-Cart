package com.example.b07_final_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class My_products_owner extends AppCompatActivity {

    private ListView OwnerProducts;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_products_owner);
        // Hide TitleBar
        getSupportActionBar().hide();

        //Get Owner ID
        Intent intent = getIntent();
        String uid = intent.getExtras().getString("Ownerid");

        OwnerProducts = (ListView) findViewById(R.id.listview_orders);

        ArrayList<String> productIdList = new ArrayList();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item, productIdList);
        OwnerProducts.setAdapter(adapter);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Test_Stores");
        ref.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                productIdList.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    // get the product ID of each product
                    String product_id = ds.child("Products").child("Product_Id").getValue(String.class);
                    // store product ID to the array list of IDs
                    productIdList.add(product_id);
                }
                // now the productIdList should be filled with order IDs
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}