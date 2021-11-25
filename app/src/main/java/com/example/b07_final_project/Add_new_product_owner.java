package com.example.b07_final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class Add_new_product_owner extends AppCompatActivity {

    EditText prdName, prdPrice, brandName;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_product_owner);
        // Hide TitleBar
        getSupportActionBar().hide();

        prdName = findViewById(R.id.editText_product_name);
        brandName = findViewById(R.id.editText_brand_name);
        prdPrice = findViewById(R.id.editText_product_price);
        btnAdd = findViewById(R.id.button_add_product);
    }
}