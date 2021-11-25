package com.example.b07_final_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity_Customer extends AppCompactActivity{
    //Declare all the tools in which you want to assign functionality

    private Button order_button, shop_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_customer);
        // Hide TitleBar
        getSupportActionBar().hide();


        // Initialize them
        order_button = findViewById(R.id.customer_order_button);
        shop_button = findViewById(R.id.customer_shop_button);



        // Get ownerid to check who logged in
        Intent intent = getIntent();
        String uid = intent.getExtras().getString("Userid");


        // When this button is clicked perform the following task
        order_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Open up another activity
                Intent intent1 = new Intent(MainActivity_Customer.this, MainActivity.class);
                // send this ownerid to the activity
                intent1.putExtra("Userid", uid);
                startActivity(intent);
            }
        });

        shop_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Open up another activity
                Intent intent1 = new Intent(MainActivity_Customer.this, MainActivity.class);
                intent1.putExtra("Userid", uid);
                // send this ownerid to the activity
                startActivity(intent);
            }
        });



}
