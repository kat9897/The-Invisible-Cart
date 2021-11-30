package com.example.b07_final_project.owner_dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.b07_final_project.helper.CustomListAdapter;
import com.example.b07_final_project.R;
import com.example.b07_final_project.helper.Product_Card;

import java.util.ArrayList;

public class OrderPage extends AppCompatActivity {

    // This page is shown when Store Owner clicks on an order ID in All Orders page.

    private Button btnComplete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_page_owner);
        // Hide TitleBar
        getSupportActionBar().hide();
        ListView listView = (ListView) findViewById(R.id.productListView);

        ArrayList<Product_Card> productList = new ArrayList<>();
        //singleton.getProducts(OrderID) needs to return a arraylist of Product_Card(which contains
        //the name, price and quantity of a product)
        productList.add(new Product_Card("name", "price", "quantity"));

        CustomListAdapter adapter = new CustomListAdapter(this, R.layout.cardview_product, productList);
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

            }
        });
    }
}