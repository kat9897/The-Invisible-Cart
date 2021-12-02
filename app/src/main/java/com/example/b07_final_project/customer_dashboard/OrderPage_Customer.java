package com.example.b07_final_project.customer_dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.b07_final_project.R;
import com.example.b07_final_project.helper.CustomListAdapter;
import com.example.b07_final_project.helper.Customer;
import com.example.b07_final_project.helper.Order_;
import com.example.b07_final_project.helper.Presenter;
import com.example.b07_final_project.helper.Product_;
import com.example.b07_final_project.helper.Product_Card;
import com.example.b07_final_project.helper.Singleton;

import java.util.ArrayList;

public class OrderPage_Customer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_page_customer);
        ListView listView = (ListView) findViewById(R.id.productListView);

        Presenter singleton = Singleton.getID();

        Order_ order = singleton.getViewedOrder();
        Customer customer = singleton.getLoggedInCustomer();
        ArrayList<Product_> productsInOrder = singleton.getProducts(order);

        String customerName = customer.getName();
        int orderStatusNum = order.getStatus();
        String orderStatus = "Not ready for pick up";
        if (orderStatusNum == Order_.COMPLETE){
            orderStatus = "Ready for pick up";
        }


        ArrayList<Product_Card> productList = new ArrayList<>();

        for (Product_ product : productsInOrder) {

            String name = product.getName();
            String brand = product.getBrand();
            String price = String.valueOf(product.getPrice());
            // shorten string to 2 decimals?

            String quantity = String.valueOf(singleton.getQuantity(order, product));

            Product_Card productCard = new Product_Card(name, price, quantity, brand);
            productList.add(productCard);
        }

        productList.add(new Product_Card("name", "price", "quantity", "brand"));

        CustomListAdapter adapter = new CustomListAdapter(this, R.layout.view_product_order_page_owner, productList);
        listView.setAdapter(adapter);
    }
}