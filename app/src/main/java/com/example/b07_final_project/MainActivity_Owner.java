package com.example.b07_final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity_Owner extends AppCompatActivity {

    private Button btn_orders, btn_add_new_product, btn_products;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_owner);
        // Hide TitleBar
        getSupportActionBar().hide();

        btn_orders = findViewById(R.id.Orders_Button);
        btn_products = findViewById(R.id.Product_Button);
        btn_add_new_product = findViewById(R.id.Add_New_Button);

        Intent intent = getIntent();
        String uid = intent.getExtras().getString("Ownerid");

        btn_orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainActivity_Owner.this, All_orders_owner.class);
                intent1.putExtra("Ownerid", uid);
                startActivity(intent);
            }
        });

        btn_products.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainActivity_Owner.this, All_orders_owner.class);
                intent1.putExtra("Ownerid", uid);
                startActivity(intent);
                startActivity(new Intent(MainActivity_Owner.this, My_products_owner.class));
            }
        });
        btn_add_new_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainActivity_Owner.this, All_orders_owner.class);
                intent1.putExtra("Ownerid", uid);
                startActivity(intent);
                startActivity(new Intent(MainActivity_Owner.this, Add_new_product_owner.class));
            }
        });



    }





    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }
}