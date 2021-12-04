package com.example.b07_final_project.customer_dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

import java.text.DecimalFormat;
import java.util.ArrayList;

public class All_Products extends AppCompatActivity {
    private Button btnOrder;
    private ImageView updateBtn;
    private EditText qty;
    private ListView product_listview;
    private TextView totalPrice;
    private TextView nameOfStore;
    private TextView totalText;

    private ArrayList<Product_Card> productList;
    private Customer customer;
    private Store store;
    private ArrayList<Product_> productListObjects;
    private Presenter singleton;
    private ListView listView;

    private static final DecimalFormat priceFormat = new DecimalFormat("0.00");

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

        updateBtn = findViewById(R.id.updatePrice);
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Total
                Double total = 0.0;

                // loop for all the items in listview
                for (int i = 0; i < productList.size(); i++) {
                    // This is important for updating quantity values!!
                    v = listView.getChildAt(i);
                    qty = v.findViewById(R.id.product_quantity);
                    String quantity = qty.getText().toString();
                    int qtynum;

                    // If it's empty, make quantity 0, else set it equal to the int value
                    if (quantity.isEmpty())
                        qtynum = 0;
                    else
                        qtynum = Integer.parseInt(quantity);

                    // check first if there are invalid inputs, if there are immediately
                    // set total price to $0, send toast message, and exit!
                    if (qtynum > 50) {
                        productList.get(i).quantity = "0";
                        totalText = findViewById(R.id.total);
                        totalText.setText("$0.00");
                        Toast.makeText(All_Products.this, "Insert a valid number between 0-50", Toast.LENGTH_LONG).show();
                        return;
                    }
                    if (0 < qtynum && qtynum <= 50) {
                        productList.get(i).quantity = String.valueOf(qtynum);
                        total += Double.parseDouble(priceFormat.format(
                                Double.parseDouble(productList.get(i).getPrice()) * qtynum));
                    }
                }
                // Total
                totalText = findViewById(R.id.total);
                totalText.setText("$" + priceFormat.format(total));
            }
        });

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int totalProductsOrdered = 0;

                // Total
                Double total = 0.0;

                // loop for all the items in listview
                for (int i = 0; i < productList.size(); i++) {
                    // This is important for updating quantity values!!
                    v = listView.getChildAt(i);
                    qty = v.findViewById(R.id.product_quantity);
                    String quantity = qty.getText().toString();
                    int qtynum;

                    // If it's empty, make quantity 0, else set it equal to the int value
                    if (quantity.isEmpty())
                        qtynum = 0;
                    else
                        qtynum = Integer.parseInt(quantity);

                    // check if it's a valid input
                    if (0 < qtynum && qtynum <= 50) {
                        totalProductsOrdered += 1;
                        productList.get(i).quantity = String.valueOf(qtynum);
                        total += Double.parseDouble(priceFormat.format(
                                Double.parseDouble(productList.get(i).getPrice()) * qtynum));
                    } if (qtynum > 50) {
                        // if there's an invalid input, immediately send toast, and exit!!
                        productList.get(i).quantity = "0";
                        Toast.makeText(All_Products.this, "Insert a valid number between 0-50", Toast.LENGTH_LONG).show();
                        return;
                    }
                }
                // Check if all products are empty
                if (totalProductsOrdered == 0) {
                    Toast.makeText(All_Products.this, "Insert a valid number between 0-50", Toast.LENGTH_LONG).show();
                    btnOrder.setText("ORDER");
                    return;
                }
                // Total
                totalText = findViewById(R.id.total);
                totalText.setText("$" + priceFormat.format(total));

                Order_ order = singleton.newOrder(customer, store);
                for (Product_Card pc : productList)
                    // ignore any with quantity 0
                    if (!pc.getQuantity().equals("0") ) {
                        if(!pc.getQuantity().equals("")){
                            singleton.addProductToOrder(order, pc.getID(), Integer.valueOf(pc.getQuantity()));
                        }
                    }
                btnOrder.setText("ORDER PLACED");
                    /* Checked before this
                    if (singleton.getProducts(order).isEmpty()) {
                        Toast.makeText(All_Products.this, "Please place an order with valid quantities.", Toast.LENGTH_LONG).show();
                    }*/
                // Intent
                startActivity(new Intent(All_Products.this, Main_Customer.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(All_Products.this, All_Store.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }
}