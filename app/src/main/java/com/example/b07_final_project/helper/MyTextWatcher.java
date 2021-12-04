////package com.example.b07_final_project.helper;
////
////import android.text.Editable;
////import android.text.TextWatcher;
////import android.widget.EditText;
////
////public class MyTextWatcher  implements TextWatcher {
////    EditText et;
////
////    public MyTextWatcher(EditText editText) {
////        this.et = editText;
////    }
////
////    @Override
////    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
////
////    }
////
////    @Override
////    public void onTextChanged(CharSequence s, int start, int before, int count) {
////        if (et.getTag() != null) {
////            // This is required to ensure EditText is edited by user and not through program
////            if (et.hasFocus()) {
////                int position = (int) et.getTag();
////                String newText = et.getText() + "";
////                //Implement your actions here........
////                //you can get require things/ views from listView.getChildAt(position)..
////            }
////
////        }
////
////    }
////
////    @Override
////    public void afterTextChanged(Editable s) {
////
////    }
////}
//
//package com.example.b07_final_project.helper;
//
//import android.text.Editable;
//import android.text.TextWatcher;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.TextView;
//
//import com.example.b07_final_project.R;
//
//import java.text.DecimalFormat;
//
//public class MyTextWatcher implements TextWatcher {
//    private View view;
//    private double orderTotal;
//
//    public MyTextWatcher(View view) {
//        this.view = view;
//    }
//
//    @Override
//    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//    }
//
//    @Override
//    public void onTextChanged(CharSequence s, int start, int before, int count) {
//    }
//
//    @Override
//    public void afterTextChanged(Editable s) {
//        DecimalFormat df = new DecimalFormat("0.00##");
//        String qtyString = s.toString().trim();
//        int quantity = qtyString.equals("") ? 0:Integer.valueOf(qtyString);
//
//        EditText qtyView = (EditText) view.findViewById(R.id.product_quantity);
//        Product_Card product = (Product_Card) qtyView.getTag();
//
//        orderTotal += (double) Integer.parseInt(s.toString()) * Double.parseDouble(product.getPrice());;
//        TextView cartTotal = (TextView) view.findViewById(R.id.total);
//        cartTotal.setText(df.format(orderTotal));
//    }
//}