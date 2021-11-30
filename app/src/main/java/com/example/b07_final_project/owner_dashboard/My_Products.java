package com.example.b07_final_project.owner_dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.b07_final_project.helper.Product_Card;
import com.example.b07_final_project.R;
import com.example.b07_final_project.helper.CustomListAdapter;

import java.util.ArrayList;


public class My_Products extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_products_owner);
        // Hide TitleBar
        getSupportActionBar().hide();
        ListView listView = (ListView) findViewById(R.id.listProduct);

        ArrayList<Product_Card> productList = new ArrayList<>();
        //singleton.getProducts(OwnerID) needs to return a arraylist of Product_Card(which contains
        //the name, price and quantity of a product)
        productList.add(new Product_Card("name", "price", "quantity"));

        CustomListAdapter adapter = new CustomListAdapter(this, R.layout.cardview_product_owner, productList);
        listView.setAdapter(adapter);
    }
}