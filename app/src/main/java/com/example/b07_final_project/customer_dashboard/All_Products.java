package com.example.b07_final_project.customer_dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
    private EditText qty;
    private ListView product_listview;
    private TextView totalPrice;
    private TextView nameOfStore;

    private ArrayList<Product_Card> productList;
    private Customer customer;
    private Store store;
    private ArrayList<Product_> productListObjects;
    private Presenter singleton;
    private ListView listView;

//    private TextWatcher textWatcher = new TextWatcher() {
//        @Override
//        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//        }
//
//        @Override
//        public void onTextChanged(CharSequence s, int start, int before, int count) {
//            String qtyNumber = qty.getText().toString();
//            int number = Integer.parseInt(qtyNumber);
//            if (number < 0 && number > 50) {
//                Toast.makeText(getApplicationContext(), "Insert a valid number between 0-50", Toast.LENGTH_SHORT).show();
//            }
//            qty.setText(qtyNumber);
//        }
//
//        @Override
//        public void afterTextChanged(Editable s) {
//            String strEnteredVal = qty.getText().toString();
//            if (!strEnteredVal.equals("")) {
//                int num = Integer.parseInt(strEnteredVal);
//                if (num >= 0 && num <= 50) {
//                    qty.setText("" + num);
//                } else {
//                    qty.setText("0");
//                    Toast.makeText(getApplicationContext(), "Insert a valid number between 0-50", Toast.LENGTH_SHORT).show();
//                }
//            }
//        }
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_products);
        // Hide TitleBar
        getSupportActionBar().hide();

        btnOrder = findViewById(R.id.Order);
        qty = (EditText) findViewById(R.id.product_quantity);
        product_listview = findViewById(R.id.productListView);
        totalPrice = findViewById(R.id.total);
        nameOfStore = findViewById(R.id.heading_store);

        singleton = Singleton.getID();
        listView = findViewById(R.id.listProduct_1);

//        qty.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
////                totalPrice.setText();
////                for(int i =0 ; i < product_listview.getCount(); i++) {
////
////                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
        productList = new ArrayList<>();

        customer = singleton.getLoggedInCustomer();
        store = singleton.getViewedStore();
        productListObjects = singleton.getProducts(store);
        // Set name of store at top
        nameOfStore.setText(store.getName());

        // Add products to product list for store
        for (Product_ p : productListObjects) {

            String name = p.getName();
            String brand = p.getBrand();
            String price = String.valueOf(p.getPrice());

            Product_Card pc = new Product_Card(name, price, "", brand);
            pc.setID(p.getID());

            productList.add(pc);
        }

        CustomListAdapter2 adapter = new CustomListAdapter2(this, R.layout.cardview_product_order, productList);
        listView.setAdapter(adapter);

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int totalProductsOrdered = 0;
                // loop for all the items in listview
                for (int i = 0; i < productList.size(); i++) {
                    v =listView.getChildAt(i);
                    qty = v.findViewById(R.id.product_quantity);
                    String quantity = qty.getText().toString();
                    int qtynum;

                    if (quantity.isEmpty())
                        qtynum = 0;
                    else
                        qtynum = Integer.parseInt(quantity);

                    if (0 < qtynum && qtynum <= 50) {
                        totalProductsOrdered += 1;
                        productList.get(i).quantity = String.valueOf(qtynum);
                    } else {
                        productList.get(i).quantity = "0";
                    }
                }
                if (totalProductsOrdered == 0) {
                    Toast.makeText(All_Products.this, "Insert a valid number between 0-50", Toast.LENGTH_LONG).show();
                    return;
                }
                Order_ order = singleton.newOrder(customer, store);
                for (Product_Card pc : productList)
                    // ignore any with quantity 0
                    if (!pc.getQuantity().equals("0")) {
                        singleton.addProductToOrder(order, pc.getID(), Integer.valueOf(pc.getQuantity()));
                    }
                    if (singleton.getProducts(order) == null) {
                        Toast.makeText(All_Products.this, "Please place an order with valid quantities.", Toast.LENGTH_LONG).show();
                    }
                // Intent
                startActivity(new Intent(All_Products.this, Order_List_Customer.class));
            }
        });
    }
}