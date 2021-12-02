package com.example.b07_final_project.owner_dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.b07_final_project.helper.CustomListAdapter;
import com.example.b07_final_project.R;
import com.example.b07_final_project.helper.Customer;
import com.example.b07_final_project.helper.Order_;
import com.example.b07_final_project.helper.Presenter;
import com.example.b07_final_project.helper.Product_;
import com.example.b07_final_project.helper.Product_Card;
import com.example.b07_final_project.helper.Singleton;

import java.util.ArrayList;

public class OrderPage_Owner extends AppCompatActivity {

    // This page is shown when Store Owner clicks on an order ID in All Orders page.

    private Button btnComplete;

    Presenter singleton = Singleton.getID();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_page_owner);
        getSupportActionBar().hide();
        ListView listView = (ListView) findViewById(R.id.productListView);

        Order_ order = singleton.getViewedOrder();
        Customer customer = singleton.getCustomer(order);
        ArrayList<Product_> productsInOrder = singleton.getProducts(order);

        ArrayList<Product_Card> productList = new ArrayList<>();

        for (Product_ product : productsInOrder) {

            String name = product.getName();
            String brand = product.getBrand();
            String price = String.valueOf(product.getPrice());
            // shorten string to 2 decimals?

            String quantity = "-1";//String.valueOf(singleton.getQuantity(order, product));

            Product_Card productCard = new Product_Card(name, price, quantity, brand);
            productList.add(productCard);
        }

        productList.add(new Product_Card("name", "price", "quantity", "brand"));

        CustomListAdapter adapter = new CustomListAdapter(this, R.layout.view_product_order_page_owner, productList);
        listView.setAdapter(adapter);

        // COMPLETE Button
        btnComplete = findViewById(R.id.complete);
        btnComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                button.setText("COMPLETED"); // Text "COMPLETE" becomes COMPLETED
                button.setSelected(!button.isSelected()); // Button colour changes from Blue to Green
                //TODO Status becomes Ready for Pick Up (1)
                order.setStatus(Order_.COMPLETE);
                order.save();

            }
        });
    }
}