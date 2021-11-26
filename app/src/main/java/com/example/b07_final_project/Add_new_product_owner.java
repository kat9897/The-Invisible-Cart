package com.example.b07_final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Add_new_product_owner extends AppCompatActivity {

    EditText prdName, prdPrice, brandName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_product_owner);
        // Hide TitleBar
        getSupportActionBar().hide();

    }
    public void sendNewProduct(View view) {
        Product product = new Product();
        // Inputs
        prdName = findViewById(R.id.editName);
        prdPrice = findViewById(R.id.editPrice);
        brandName = findViewById(R.id.editBrand);
        // Conversion
        product.setName(prdName.getText().toString());
        String stringPrice = prdPrice.getText().toString();
        product.setPrice(Double.parseDouble(stringPrice));
        product.setBrand(brandName.getText().toString());

        // Check if empty
        if(TextUtils.isEmpty(prdName.getText().toString())){ // name is empty
            Toast.makeText(Add_new_product_owner.this, "Please enter name", Toast.LENGTH_SHORT).show();
            return;
        }       else if (TextUtils.isEmpty(stringPrice)){ // price is empty or not valid
            Toast.makeText(Add_new_product_owner.this, "Please enter a valid price", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(brandName.getText().toString())){ // brand is empty
            Toast.makeText(Add_new_product_owner.this, "Please enter brand", Toast.LENGTH_SHORT).show();
            return;
        } else {
            // Input to database
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("products");
            ref.push().setValue(product);
        }

        // Go to new page
        Intent intent = new Intent(Add_new_product_owner.this, My_products_owner.class);
        startActivity(intent);
    }
}