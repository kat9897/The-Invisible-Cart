package com.example.b07_final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity_Owner extends AppCompatActivity {

    // Declare all the tools to which you want to assign functionality
    private Button btn_orders, btn_add_new_product, btn_products;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_owner);
        // Hide TitleBar
        getSupportActionBar().hide();


        // Initialize them
        btn_orders = findViewById(R.id.Orders_Button);
        btn_products = findViewById(R.id.Product_Button);
        btn_add_new_product = findViewById(R.id.Add_New_Button);


        // Get ownerid to check who logged in
        Intent intent = getIntent();
        String uid = intent.getExtras().getString("Ownerid");


        // When this button is clicked perform the following task
        btn_orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Open up another activity
                Intent intent1 = new Intent(MainActivity_Owner.this, All_orders_owner.class);
                // send this ownerid to the activity
                intent1.putExtra("Ownerid", uid);
                startActivity(intent1);
            }
        });



        btn_products.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Open up another activity
                Intent intent1 = new Intent(MainActivity_Owner.this, My_products_owner.class);
                intent1.putExtra("Ownerid", uid);
                // send this ownerid to the activity
                startActivity(intent1);
            }
        });
        btn_add_new_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Open up another activity
                Intent intent1 = new Intent(MainActivity_Owner.this, Add_new_product_owner.class);
                //intent1.putExtra("Ownerid", uid);
                // send this ownerid to the activity
                startActivity(intent1);
            }
        });



    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }
}