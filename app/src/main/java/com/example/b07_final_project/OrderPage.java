package com.example.b07_final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class OrderPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_page);
        // Hide TitleBar
        getSupportActionBar().hide();
    }
}