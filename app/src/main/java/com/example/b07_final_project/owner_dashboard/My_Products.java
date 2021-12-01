package com.example.b07_final_project.owner_dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.b07_final_project.helper.CustomListAdapter1;
import com.example.b07_final_project.helper.Owner;
import com.example.b07_final_project.helper.Presenter;
import com.example.b07_final_project.helper.Product_;
import com.example.b07_final_project.helper.Product_Card;
import com.example.b07_final_project.R;
import com.example.b07_final_project.helper.CustomListAdapter;
import com.example.b07_final_project.helper.Singleton;

import java.util.ArrayList;


public class My_Products extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_products_owner);
        // Hide TitleBar
        getSupportActionBar().hide();
        ListView listView = (ListView) findViewById(R.id.listProduct);

        Presenter singleton = Singleton.getID();

        Owner owner = singleton.getLoggedInOwner();
        ArrayList<Product_> products ;

        ArrayList<Product_> productsInStore = singleton.getProducts(owner);

        ArrayList<Product_Card> productList = new ArrayList<>();

        for (Product_ product : productsInStore) {

            String name = product.getName();
            String brand = product.getBrand();
            String price = String.valueOf(product.getPrice());
            // shorten string to 2 decimals?

            Product_Card productCard = new Product_Card(name, price, brand);
            productList.add(productCard);
        }

        productList.add(new Product_Card("name", "price", "brand"));

        CustomListAdapter1 adapter = new CustomListAdapter1(this, R.layout.view_product_page_owner, productList);
        listView.setAdapter(adapter);
    }
}