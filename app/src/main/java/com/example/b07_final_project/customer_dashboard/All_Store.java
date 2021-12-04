package com.example.b07_final_project.customer_dashboard;

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
import com.example.b07_final_project.R;

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
        list.clear();
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
                singleton.viewStore(allStores.get(position));

                String clickedStoreName = listView.getAdapter().getItem(position).toString();
                Intent intent = new Intent(getApplicationContext(), All_Products.class);
                intent.putExtra("heading_store", clickedStoreName);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(All_Store.this, Main_Customer.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }
}
