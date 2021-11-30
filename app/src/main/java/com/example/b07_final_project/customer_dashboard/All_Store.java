package com.example.b07_final_project.customer_dashboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.b07_final_project.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class All_Store extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_store);
        // Hide TitleBar
        getSupportActionBar().hide();

        listView = (ListView) findViewById(R.id.StoreList);
        final ArrayList<String> storeNamesList = new ArrayList<String>();
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item, storeNamesList);
        listView.setAdapter(adapter);

        // Fetch all the Store Names in the Database to the storeNamesList
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Test_Stores");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                storeNamesList.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    String storeName = ds.child("Store_Name").getValue().toString();
                    storeNamesList.add(storeName);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //TODO When a Store Name is clicked, it will direct the Customer to the
                // Order page containing List of Products belonging to the Store Name clicked

                // String clickedStoreName = listView.getAdapter().getItem(position).toString();
                Intent intent = new Intent(getApplicationContext(), All_Products.class);
                startActivity(intent);
            }
        });
    }
}