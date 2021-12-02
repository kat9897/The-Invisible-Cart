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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_products);
        // Hide TitleBar
        getSupportActionBar().hide();

        btnOrder = findViewById(R.id.Order);
        qty = findViewById(R.id.product_quantity);
        product_listview = findViewById(R.id.productListView);
        totalPrice = findViewById(R.id.total);

        Presenter singleton = Singleton.getID();
        ListView listView = findViewById(R.id.listProduct_1);

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
            ArrayList<Product_Card> orderproducts = new ArrayList<>();
            // loop for all the items in listview
            for(int i =0 ; i < product_listview.getCount(); i++){

                String quantity = qty.getText().toString();

                int qtynum;

                qtynum = Integer.parseInt(quantity);

                if (!(0 <= qtynum &&  qtynum <= 50)){
                    Toast.makeText(All_Products.this, "Insert a valid number between 0-50", Toast.LENGTH_LONG);
                    return;
                }

                productList.get(i).quantity = String.valueOf(qtynum);

            }
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