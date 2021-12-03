package com.example.b07_final_project.customer_dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.b07_final_project.R;
import com.example.b07_final_project.helper.CustomListAdapter;
import com.example.b07_final_project.helper.Customer;
import com.example.b07_final_project.helper.Order_;
import com.example.b07_final_project.helper.Presenter;
import com.example.b07_final_project.helper.Product_;
import com.example.b07_final_project.helper.Product_Card;
import com.example.b07_final_project.helper.Singleton;
import com.example.b07_final_project.helper.Store;

import java.util.ArrayList;

public class OrderPage_Customer extends AppCompatActivity {

    private TextView nameOfStore;
    private Button statusBtn;
    private String orderStatus;
    private ListView listView;

    private Store store;
    private Presenter singleton;
    private Order_ order;
    private Customer customer;
    private ArrayList<Product_> productsInOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_page_customer);
        // Hide TitleBar
        getSupportActionBar().hide();

        listView = (ListView) findViewById(R.id.productListView);

        singleton = Singleton.getID();
        order = singleton.getViewedOrder();
        customer = singleton.getLoggedInCustomer();
        String customerName = customer.getName();
        productsInOrder = singleton.getProducts(order);

        // Display Store Name
//        nameOfStore = findViewById(R.id.storeName);
//        store = singleton.getViewedStore();
//        nameOfStore.setText(store.getName());

        // Display Status
        int orderStatusNum = order.getStatus();
        orderStatus = "Not ready for pick up";
        if (orderStatusNum == Order_.COMPLETE){
            orderStatus = "Ready for pick up";
        }
        statusBtn = findViewById(R.id.statusOrder);
        statusBtn.setText(orderStatus);

        ArrayList<Product_Card> productList = new ArrayList<>();
        
        for (Product_ product : productsInOrder) {

            String name = product.getName();
            String brand = product.getBrand();
            String price = String.valueOf(product.getPrice());
            // shorten string to 2 decimals?

            String quantity = "-1";
            // String quantity = String.valueOf(singleton.getQuantity(order, product));

            Product_Card productCard = new Product_Card(name, price, quantity, brand);
            productList.add(productCard);
        }

        // productList.add(new Product_Card("name", "price", "quantity", "brand"));

        CustomListAdapter adapter = new CustomListAdapter(this, R.layout.view_product_order_page_owner, productList);
        listView.setAdapter(adapter);
    }
}