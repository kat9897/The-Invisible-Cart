package com.example.b07_final_project.helper;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
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
        TextView quantity;
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