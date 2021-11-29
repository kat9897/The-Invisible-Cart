package com.example.b07_final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class OrderPage extends AppCompatActivity {

    // This page is shown when Store Owner clicks on an order ID in All Orders page.

    private TextView orderHeading;
    private TextView customerName;
    private TextView totalPrice;
    private Button btnComplete;
    private ListView productListView;
    private ArrayList<String> productNames;
    private ArrayList<Double> productPrices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_page);
        // Hide TitleBar
        getSupportActionBar().hide();

        // Set the Order Number, Customer Email, Total Price from the Clicked Order ID on this Page
        orderHeading = findViewById(R.id.heading_orders2);
        customerName = findViewById(R.id.custName);
        totalPrice = findViewById(R.id.total);
        Intent intent = getIntent();
        orderHeading.setText(intent.getExtras().getString("orderHeading"));
        customerName.setText(intent.getExtras().getString("customerName"));
        totalPrice.setText(intent.getExtras().getString("totalPrice"));

        // List each items in the Product + Price + Quantity


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

    // Function to get the Quantity of a Specific Product in a Given Order
    public int getQuantity(Order order, String prdctName){
        int quantity = 0;
        // List of Products in the Given Order
        ArrayList<Product> orderPrdctsList = new ArrayList<Product>();
        orderPrdctsList = order.getProducts();
        // Compare our Specific Product to Each of the Products in the Given Order
        if (orderPrdctsList.contains(prdctName)) {
            for (Product prdct : orderPrdctsList) {
                String currentPrdctName = prdct.getName();
                if (currentPrdctName.equals(prdctName)) {
                    quantity++;
                }
            }
        }
        return quantity;
    }

}