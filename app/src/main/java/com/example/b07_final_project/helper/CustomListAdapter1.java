package com.example.b07_final_project.helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.b07_final_project.R;

import java.util.ArrayList;

public class CustomListAdapter1 extends ArrayAdapter<Product_Card> {
    private Context mContext;
    private int mResource;

    public CustomListAdapter1(Context context, int resource, ArrayList<Product_Card> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        String name = getItem(position).getName();
        String price = getItem(position).getPrice();
        String brand = getItem(position).getBrand();

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvName = convertView.findViewById(R.id.product_name);
        TextView tvPrice = convertView.findViewById(R.id.product_price);

        tvName.setText(name + " (" + brand + ")");
        tvPrice.setText("$" + price);

        return convertView;
    }
}
