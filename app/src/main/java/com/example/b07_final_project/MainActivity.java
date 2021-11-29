package com.example.b07_final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    //Declare all the tools in which you want to assign functionality

    private Button allStoresBtn;
    private Button myOrdersBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Hide TitleBar
        getSupportActionBar().hide();

        allStoresBtn = findViewById(R.id.AllStoresBtn);
        myOrdersBtn = findViewById(R.id.MyOrdersBtn);

        allStoresBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AllStoreActivity.class);
                startActivity(intent);
            }
        });

        myOrdersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AllStoreActivity.class);
                startActivity(intent);
            }
        });

    }
}