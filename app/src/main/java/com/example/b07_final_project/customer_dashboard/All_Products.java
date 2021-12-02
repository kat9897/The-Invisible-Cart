package com.example.b07_final_project.customer_dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.b07_final_project.R;
import com.example.b07_final_project.helper.CustomListAdapter;
import com.example.b07_final_project.helper.CustomListAdapter2;
import com.example.b07_final_project.helper.Customer;
import com.example.b07_final_project.helper.Order_;
import com.example.b07_final_project.helper.Presenter;
import com.example.b07_final_project.helper.Product_;
import com.example.b07_final_project.helper.Product_Card;
import com.example.b07_final_project.helper.Singleton;
import com.example.b07_final_project.helper.Store;

import java.util.ArrayList;

public class All_Products extends AppCompatActivity {
    private Button btnOrder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_products);
        // Hide TitleBar
        getSupportActionBar().hide();

        Presenter singleton = Singleton.getID();
        ListView listView = (ListView) findViewById(R.id.listProduct_1);

        ArrayList<Product_Card> productList = new ArrayList<>();

        Customer customer = singleton.getLoggedInCustomer();
        Store store = singleton.getViewedStore();
        ArrayList<Product_> productListObjects = singleton.getProducts(store);

        for (Product_ p : productListObjects) {

            String name = p.getName();
            String brand = p.getBrand();
            String price = String.valueOf(p.getPrice());

            Product_Card pc = new Product_Card(name, price, "0", brand);
            pc.setID(p.getID());

            productList.add(pc);
        }


//        singleton.getProducts()
        // needs to return a arraylist of Product_Card(which contains
        //the name, price and quantity of a product)
        productList.add(new Product_Card("name", "price", "0", "brand"));

        CustomListAdapter2 adapter = new CustomListAdapter2(this, R.layout.cardview_product_order, productList);
        listView.setAdapter(adapter);

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO DATABASE
                Order_ order = singleton.newOrder(customer, store);
                for (Product_Card pc : productList)
                    singleton.addProductToOrder(order, pc.getID(), Integer.valueOf(pc.getQuantity()));
                    // ignores any with 0 quantity

                // Intent
                startActivity(new Intent(All_Products.this, Order_List_Customer.class));
            }
        });
    }
}