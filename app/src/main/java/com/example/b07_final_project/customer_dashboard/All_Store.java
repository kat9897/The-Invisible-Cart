package com.example.b07_final_project.customer_dashboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.b07_final_project.helper.Presenter;
import com.example.b07_final_project.helper.Singleton;
import com.example.b07_final_project.helper.Store;
import com.example.b07_final_project.customer_dashboard.OrderPage_Customer;
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

        listView = findViewById(R.id.StoreList);
        ArrayList<String> list = new ArrayList<>();

        Presenter singleton = Singleton.getID();
        // Fetch all the Store Names in the Database to the storeNamesList
        ArrayList<Store> allStores = singleton.allStores();
        for (int i = 0; i < allStores.size(); i++){
            list.add(allStores.get(i).getName());
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,list);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //TODO When a Store Name is clicked, it will direct the Customer to the
                // Order page containing List of Products belonging to the Store Name clicked

                String clickedStoreName = listView.getAdapter().getItem(position).toString();
                Intent intent = new Intent(getApplicationContext(), All_Products_Alternative.class);
                intent.putExtra("heading_store", clickedStoreName);
                startActivity(intent);
            }
        });
    }
}
