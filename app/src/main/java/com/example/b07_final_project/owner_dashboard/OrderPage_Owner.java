package com.example.b07_final_project.owner_dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.b07_final_project.helper.CustomListAdapter;
import com.example.b07_final_project.R;
import com.example.b07_final_project.helper.Customer;
import com.example.b07_final_project.helper.Order_;
import com.example.b07_final_project.helper.Presenter;
import com.example.b07_final_project.helper.Product_;
import com.example.b07_final_project.helper.Product_Card;
import com.example.b07_final_project.helper.Singleton;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class OrderPage_Owner extends AppCompatActivity {

    // This page is shown when Store Owner clicks on an order ID in All Orders page.

    private Button btnComplete;
    private TextView displayCustomerName;
    private TextView totalText;
    Presenter singleton = Singleton.getID();
    private static final DecimalFormat priceFormat = new DecimalFormat("0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_page_owner);
        getSupportActionBar().hide();
        ListView listView = (ListView) findViewById(R.id.productListView);

        Order_ order = singleton.getViewedOrder();
        Customer customer = singleton.getCustomer(order);
        ArrayList<Product_> productsInOrder = singleton.getProducts(order);

        displayCustomerName = findViewById(R.id.custName);
        displayCustomerName.setText(customer.getName());

        // Total
        Double total = 0.0;

        ArrayList<Product_Card> productList = new ArrayList<>();

        for (Product_ product : productsInOrder) {

            String name = product.getName();
            String brand = product.getBrand();
            String price = priceFormat.format(product.getPrice());
            total += Double.parseDouble(priceFormat.format(product.getPrice()));

            String quantity = String.valueOf(singleton.getQuantity(order, product));

            Product_Card productCard = new Product_Card(name, price, quantity, brand);
            productList.add(productCard);
        }

        // Total
        totalText = findViewById(R.id.total);
        totalText.setText("$" + priceFormat.format(total));

        CustomListAdapter adapter = new CustomListAdapter(this, R.layout.view_product_order_page_owner, productList);
        listView.setAdapter(adapter);

        // COMPLETE Button
        btnComplete = findViewById(R.id.complete);
        btnComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                button.setEnabled(false);
                button.setText("COMPLETED"); // Text "COMPLETE" becomes COMPLETED
                button.setSelected(!button.isSelected()); // Button colour changes from Blue to Green
                button.setSelected(true); // Button stays Green
                // Status becomes Ready for Pick Up
                order.setStatus(Order_.COMPLETE);
                order.save();
            }
        });
        // Button needs to stay COMPLETED + Green if previously clicked when going back to this Order Page
        String orderStatus = "COMPLETE";
        int orderStatusNum = order.getStatus();
        if (orderStatusNum == Order_.COMPLETE){
            btnComplete.setEnabled(false);
            orderStatus = "COMPLETED";
            btnComplete.setSelected(!btnComplete.isSelected());
            btnComplete.setSelected(true);
        }
        btnComplete.setText(orderStatus);
    }
}