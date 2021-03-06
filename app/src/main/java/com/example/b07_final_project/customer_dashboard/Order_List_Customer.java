package com.example.b07_final_project.customer_dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.b07_final_project.R;
import com.example.b07_final_project.helper.Customer;
import com.example.b07_final_project.helper.Order;
import com.example.b07_final_project.helper.Presenter;
import com.example.b07_final_project.helper.Singleton;

import java.util.ArrayList;

public class Order_List_Customer extends AppCompatActivity {

    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list_customer);
        // Hide TitleBar
        getSupportActionBar().hide();

        listView = findViewById(R.id.OrderList_1);
        ArrayList<String> list = new ArrayList<>();

        Presenter singleton = Singleton.getID();
        Customer customer = singleton.getLoggedInCustomer();
        ArrayList<Order> orders = singleton.getOrders(customer);

        int j = 1;
        for (Order o : orders) {
            list.add("Order number " + j);
            j++;
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,list);
        listView.setAdapter(arrayAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                singleton.viewOrder(orders.get(i));
                singleton.viewStore(singleton.getStore(orders.get(i)));

                Intent intent = new Intent(getApplicationContext(), OrderPage_Customer.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Order_List_Customer.this, Main_Customer.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }
}