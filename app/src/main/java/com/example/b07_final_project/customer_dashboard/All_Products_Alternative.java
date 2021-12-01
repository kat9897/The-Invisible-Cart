package com.example.b07_final_project.customer_dashboard;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.b07_final_project.R;
import com.example.b07_final_project.helper.CustomListAdapter1;
import com.example.b07_final_project.helper.Owner;
import com.example.b07_final_project.helper.Presenter;
import com.example.b07_final_project.helper.Product_;
import com.example.b07_final_project.helper.Product_Card;
import com.example.b07_final_project.helper.Singleton;

import java.util.ArrayList;

public class All_Products_Alternative extends AppCompatActivity {

    private TextView storeHeading;
    private ListView listView;
    private Button orderBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_products_alternative);
        // Hide TitleBar
        getSupportActionBar().hide();

        // Set the Store Name from the Clicked Store Name on this Page
        storeHeading = findViewById(R.id.heading_store);
        Intent intent = getIntent();
        storeHeading.setText(intent.getExtras().getString("heading_store"));

        listView = (ListView) findViewById(R.id.productListView);

        Presenter singleton = Singleton.getID();

        // Get the owner whose Store Name is clicked
        Owner owner = singleton.getLoggedInOwner();
        ArrayList<Product_> productsInStore = singleton.getProducts(owner);

        ArrayList<Product_Card> productList = new ArrayList<>();
        for (Product_ product : productsInStore) {

            String name = product.getName();
            String brand = product.getBrand();
            String price = String.valueOf(product.getPrice());

            Product_Card productCard = new Product_Card(name, price, brand);
            productList.add(productCard);
        }

        productList.add(new Product_Card("name", "price", "brand"));

        CustomListAdapter1 adapter = new CustomListAdapter1(this, R.layout.view_product_page_owner, productList);
        listView.setAdapter(adapter);
//
//        ArrayList<Product_> Order = new ArrayList<>();

//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//                final String productName = adapter.getItem(position).getName();
//                final String productPrice = adapter.getItem(position).getPrice();
//                final String productBrand = adapter.getItem(position).getBrand();
//
//                AlertDialog.Builder builder = new AlertDialog.Builder(All_Products_Alternative.this);
//                builder.setMessage(productName + " has been selected")
//                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                Toast.makeText(getApplicationContext(), productName + " is added to the cart", Toast.LENGTH_SHORT).show();
//                                // add the product to an order
//                                // Order.add(new Product_(productName, productPrice, productBrand);
//                            }
//                        })
//                        .setNegativeButton("Cancel", null);
//                AlertDialog alert = builder.create();
//                alert.show();
//            }
//        });
    }

//    public void calculateBill(View view) {
//        orderBtn = findViewById(R.id.placeOrderBtn)
//        orderBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), BillingPage.class);
//                intent.putExtra("Bill", cart);
//                startActivity(intent);
//            }
//        });
//    }
}