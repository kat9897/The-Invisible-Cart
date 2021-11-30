package com.example.b07_final_project.owner_dashboard;

import static java.lang.Double.parseDouble;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.b07_final_project.helper.Presenter;
import com.example.b07_final_project.helper.Product;
import com.example.b07_final_project.R;
import com.example.b07_final_project.helper.Product_;
import com.example.b07_final_project.helper.Singleton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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

                addNewProduct(pdtName, pdtPrice, pdtBrand);
            }
        });

    }
    public void addNewProduct(String pdtName, Double pdtPrice, String pdtBrand) {

        Product_ product = singleton.newProduct(pdtName, pdtPrice, pdtBrand);

        Intent intent = new Intent(Add_New_Product.this, My_Products.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}