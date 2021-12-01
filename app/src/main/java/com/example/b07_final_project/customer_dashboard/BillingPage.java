package com.example.b07_final_project.customer_dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.b07_final_project.R;

import java.util.HashMap;
import java.util.Map;

public class BillingPage extends AppCompatActivity {

    TextView fullBill;
    String output;
    int total = 0;
    HashMap<String, Integer> bill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billing_page);

        fullBill = findViewById(R.id.bill);
        Intent intent = getIntent();

        bill = (HashMap<String, Integer>) intent.getSerializableExtra("Bill");
        for (Map.Entry<String, Integer> e : bill.entrySet()) {
            total +=e.getValue();
            output +=e.getKey()+"\t"+e.getValue()+"\n";
        }
        output += "Total Amount: " + total;
        fullBill.setText(output);
    }
}