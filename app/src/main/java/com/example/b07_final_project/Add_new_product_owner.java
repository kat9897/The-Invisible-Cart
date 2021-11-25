package com.example.b07_final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Add_new_product_owner extends AppCompatActivity {

    EditText prdName, prdPrice, brandName;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_product_owner);
        // Hide TitleBar
        getSupportActionBar().hide();

    }
    public void sendNewProduct(View view) {
        //Product product = this.view.getNewProductValues();
        Product product = new Product();
        // Inputs
        EditText editName = findViewById(R.id.editName);
        EditText editPrice = findViewById(R.id.editPrice);
        EditText editBrand = findViewById(R.id.editBrand);
        // Conversion
        product.setName(editName.getText().toString());
        String stringPrice = editPrice.getText().toString();
        product.setPrice(Double.parseDouble(stringPrice));
        product.setBrand(editBrand.getText().toString());
        // Input to database
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.child("products").child("p4").setValue(product);
        //model.addToProducts(product, "products", "p4");
        // Go to new page
        Intent intent = new Intent(this, My_products_owner.class);
        startActivity(intent);

        //this.view.activityChange(DisplayNewProductActivity.class);
    }
}