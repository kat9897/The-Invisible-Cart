package com.example.b07_final_project.helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.b07_final_project.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class CustomListAdapter2 extends ArrayAdapter<Product_Card> {
    private Context mContext;
    private int mResource;

    private static class ViewHolder{
        TextView name;
        TextView price;
        EditText quantity;
        TextView brand;
    }
    public CustomListAdapter2(Context context, int resource, ArrayList<Product_Card> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){

//        String name, price, brand;
//        TextView tvName, tvPrice;
//        LayoutInflater mInflater;
//
//        final ViewHolder holder;
//        convertView = null;
//        if (convertView == null) {
//            holder = new ViewHolder();
//            mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            convertView = mInflater.inflate(R.layout.cardview_product_order, null);
//            holder.quantity = (EditText) convertView.findViewById(R.id.product_quantity);
//            holder.quantity.setTag(position);
//            holder.quantity.setText(getItem(position).getQuantity());
//            convertView.setTag(holder);
//        }else {
//            holder = (ViewHolder) convertView.getTag();
//        }
//        int tag_position=(Integer) holder.quantity.getTag();
//        holder.quantity.setId(tag_position);
//
//        name = getItem(position).getName();
//        price = getItem(position).getPrice();
//        brand = getItem(position).getBrand();
//        tvName = (TextView) convertView.findViewById(R.id.product_name);
//        tvPrice = (TextView) convertView.findViewById(R.id.product_price);
//        tvName.setText(name + " (" + brand + ")");
//        tvPrice.setText(price);
//
//        holder.quantity.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                final int position2 = holder.quantity.getId();
//                final EditText Caption = (EditText) holder.quantity;
//                int num = Integer.parseInt(Caption.getText().toString());
//                if(num >= 0 && num <= 50) {
//                    Product_Card product = new Product_Card(name, price, Caption.toString(), brand);
//                }else{
//                    Toast.makeText(mContext, "Insert a valid number between 0-50", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//            }
//
//        });
//
//        return convertView;

        String name = getItem(position).getName();
        String price = getItem(position).getPrice();
        String quantity = getItem(position).getQuantity();
        String brand = getItem(position).getBrand();

        Product_Card product = new Product_Card(name, price, quantity, brand);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvName = (TextView) convertView.findViewById(R.id.product_name);
        TextView tvPrice = (TextView) convertView.findViewById(R.id.product_price);
        TextView tvQuantity = (TextView) convertView.findViewById(R.id.product_quantity);

        tvName.setText(name + " (" + brand + ")");
        tvPrice.setText(price);
        tvQuantity.setText(quantity);

        return convertView;
    }

}