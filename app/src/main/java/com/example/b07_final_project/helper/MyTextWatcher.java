package com.example.b07_final_project.helper;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.b07_final_project.R;

import java.text.DecimalFormat;

public class MyTextWatcher implements TextWatcher{
    private View view;
    private TextView price;
    MyTextWatcher(View view) {
        this.view = view;
        price = price.findViewById(R.id.total);
    }

    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        //do nothing
    }
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        //do nothing
    }
    public void afterTextChanged(Editable s) {

        String qtyString = s.toString().trim();
        EditText qtyView =  view.findViewById(R.id.product_quantity);
        String receiveQtyStr = s.toString();
        if (receiveQtyStr.equals(null) || receiveQtyStr.equals("")) {
            receiveQtyStr = "0";
        }
        if (view.getTag() != null) {
            // This is required to ensure EditText is edited by user and not through program
            if (view.hasFocus()) {
                int position = (int) view.getTag();

////                String newText = view.getText() + "";
//                int qty = Integer.parseInt(newText);
//                int prvprice = Integer.valueOf(price.getText().toString());
//                price.setText( prvprice + qty ); // TODO --> get the price corresponding to the position


            }
        }
    }
}