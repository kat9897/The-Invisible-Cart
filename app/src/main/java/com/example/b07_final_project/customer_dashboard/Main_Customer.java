package com.example.b07_final_project.customer_dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.b07_final_project.R;

public class Main_Customer extends AppCompatActivity {
    //Declare all the tools in which you want to assign functionality

    private Button allStoresBtn;
    private Button myOrdersBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_customer);
        // Hide TitleBar
        getSupportActionBar().hide();

        allStoresBtn = findViewById(R.id.AllStoresBtn);
        myOrdersBtn = findViewById(R.id.MyOrdersBtn);

        allStoresBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), All_Store.class);
                startActivity(intent);
            }
        });

        myOrdersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Order_List_Customer.class);
                startActivity(intent);
            }
        });

    }
}