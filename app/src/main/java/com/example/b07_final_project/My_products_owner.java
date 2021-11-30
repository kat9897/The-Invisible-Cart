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

        OwnerProducts = (ListView) findViewById(R.id.StoreList);
        final ArrayList<String> productIDList = new ArrayList<String>();
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item, productIDList);
        OwnerProducts.setAdapter(adapter);

        // Fetch all the Store Names in the Database to the storeNamesList
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Test_Stores").child("Walmart").child("Products");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                productIDList.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    String storeName = ds.child("Product_Id").getValue().toString();
                    productIDList.add(storeName);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });






    }
}