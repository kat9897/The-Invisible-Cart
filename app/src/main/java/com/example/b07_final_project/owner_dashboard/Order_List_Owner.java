package com.example.b07_final_project.owner_dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.b07_final_project.R;
import com.example.b07_final_project.helper.Order_;
import com.example.b07_final_project.helper.Owner;
import com.example.b07_final_project.helper.Presenter;
import com.example.b07_final_project.helper.Singleton;


import java.util.ArrayList;

public class Order_List_Owner extends AppCompatActivity {


    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list_owner);
        // Hide TitleBar
        getSupportActionBar().hide();

        Presenter singleton = Singleton.getID();

        listView = (ListView) findViewById(R.id.OrderList);
        ArrayList<String> orderListStrings = new ArrayList<>();

        Owner owner = singleton.getLoggedInOwner();
        ArrayList<Order_> orderListObjects = singleton.getOrders(owner);


        for (int i = 1; i <= orderListObjects.size(); i++)
            orderListStrings.add("Order Number " + String.valueOf(i));


        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,orderListStrings);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                singleton.viewOrder(orderListObjects.get(i));
                Intent intent = new Intent(getApplicationContext(), OrderPage_Owner.class);
                startActivity(intent);
            }
        });
    }
}