package com.example.b07_final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    //Declare all the tools in which you want to assign functionality

    private Button order_button, shop_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Hide TitleBar
        getSupportActionBar().hide();

        order_button = findViewById(R.id.orders_Button);
        shop_button = findViewById(R.id.product_Button);



        // Get ownerid to check who logged in
        Intent intent = getIntent();
        String uid = intent.getExtras().getString("Userid");


        // When this button is clicked perform the following task
        order_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Open up another activity
                //Intent intent1 = new Intent(MainActivity.this, #########.class);
                // send this ownerid to the activity
//                intent1.putExtra("Userid", uid);
//                startActivity(intent1);
            }
        });

        shop_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Open up another activity
//                Intent intent1 = new Intent(MainActivity.this, ######.class);
//                intent1.putExtra("Userid", uid);
//                // send this ownerid to the activity
//                startActivity(intent1);
            }
        });

    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }
}