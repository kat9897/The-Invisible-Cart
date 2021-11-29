package com.example.b07_final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;


public class My_products_owner extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_products_owner);
        // Hide TitleBar
        getSupportActionBar().hide();
        ListView listView = (ListView) findViewById(R.id.listProduct);

        ArrayList<Product_Card> productList = new ArrayList<>();
        productList.add(new Product_Card("name", "price", "quantity"));

        CustomListAdapter adapter = new CustomListAdapter(this, R.layout.cardview_product, productList);
        listView.setAdapter(adapter);
    }
}