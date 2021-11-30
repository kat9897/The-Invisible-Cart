package com.example.b07_final_project.customer_dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.b07_final_project.R;
import com.example.b07_final_project.owner_dashboard.OrderPage;

import java.util.ArrayList;

public class Order_List_Customer extends AppCompatActivity {

    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list_customer);
        // Hide TitleBar
        getSupportActionBar().hide();

        listView = (ListView) findViewById(R.id.OrderList_1);
        ArrayList<String> orderList = new ArrayList<>();

        // TODO singleton.getOrders(OwnerID) needs to return a arraylist of string where each string is a order number
        orderList.add("Order #1");


        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,orderList);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), OrderPage.class);
                startActivity(intent);
            }
        });
    }
}