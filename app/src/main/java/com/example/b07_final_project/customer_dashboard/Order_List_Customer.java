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
import com.example.b07_final_project.helper.Customer;
import com.example.b07_final_project.helper.Order_;
import com.example.b07_final_project.owner_dashboard.OrderPage_Owner;
import com.example.b07_final_project.helper.Customers;
import com.example.b07_final_project.helper.Presenter;
import com.example.b07_final_project.helper.Product;
import com.example.b07_final_project.helper.Singleton;
import com.example.b07_final_project.helper.Store;
import com.example.b07_final_project.owner_dashboard.OrderPage_Owner;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
        ArrayList<Order_> orders = singleton.getOrders(customer);

        int j = 1;
        for (Order_ o : orders) {
            list.add("Order number " + j);
            j++;
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,list);
        listView.setAdapter(arrayAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                singleton.viewOrder(orders.get(i));

                Intent intent = new Intent(getApplicationContext(), OrderPage_Customer.class);
                startActivity(intent);
            }
        });
    }
}