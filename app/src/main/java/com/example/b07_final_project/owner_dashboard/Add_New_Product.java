package com.example.b07_final_project.owner_dashboard;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.b07_final_project.helper.Presenter;
import com.example.b07_final_project.R;
import com.example.b07_final_project.helper.Product_;
import com.example.b07_final_project.helper.Singleton;

public class Add_New_Product extends AppCompatActivity {

    EditText editPdtName, prdPrice, brandName;
    Button addProduct;

    final Presenter singleton = Singleton.getID();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_product_owner);
        // Hide TitleBar
        getSupportActionBar().hide();

        // Inputs
        editPdtName = findViewById(R.id.editName);
        prdPrice = findViewById(R.id.editPrice);
        brandName = findViewById(R.id.editBrand);
        // Button
        addProduct = findViewById(R.id.button_add_product);

        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Conversion
                String pdtName = editPdtName.getText().toString();
                String stringPrice = prdPrice.getText().toString();
                Double pdtPrice = Double.parseDouble(stringPrice);
                String pdtBrand = brandName.getText().toString();

                // Check if empty
                if(TextUtils.isEmpty(pdtName)){ // name is empty
                    Toast.makeText(Add_New_Product.this, "Please enter name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(stringPrice) || pdtPrice <= 0.0){ // price is empty or not valid
                    Toast.makeText(Add_New_Product.this, "Please enter a valid price", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(pdtBrand)){ // brand is empty
                    Toast.makeText(Add_New_Product.this, "Please enter brand", Toast.LENGTH_SHORT).show();
                    return;
                }
                // Check if price is too high
                if(pdtPrice > 999.99) {
                    Toast.makeText(Add_New_Product.this, "Price is wayyy too high...", Toast.LENGTH_SHORT).show();
                    return;
                }

                addNewProduct(pdtName, pdtPrice, pdtBrand);
            }
        });

    }
    public void addNewProduct(String pdtName, Double pdtPrice, String pdtBrand) {

        Product_ product = singleton.newProduct(pdtName, pdtPrice, pdtBrand);

        Intent intent = new Intent(Add_New_Product.this, My_Products.class);
        Toast.makeText(Add_New_Product.this, "Successfully added new product: " + pdtName, Toast.LENGTH_SHORT).show();
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Add_New_Product.this, Main_Owner.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }


    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }
}