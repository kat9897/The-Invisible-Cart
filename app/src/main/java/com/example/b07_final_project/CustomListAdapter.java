package com.example.b07_final_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CustomListAdapter extends ArrayAdapter<Product_Card> {

    private Context mContext;
    private int mResource;

    private static class ViewHolder{
        TextView name;
        TextView price;
        TextView quantity;
    }
    public CustomListAdapter(Context context, int resource, ArrayList<Product_Card> objects) {
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

        Product_Card product = new Product_Card(name, price, quantity);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvName = (TextView) convertView.findViewById(R.id.product_name);
        TextView tvPrice = (TextView) convertView.findViewById(R.id.product_price);
        TextView tvQuantity = (TextView) convertView.findViewById(R.id.product_quantity);

        tvName.setText(name);
        tvPrice.setText(price);
        tvQuantity.setText(quantity);

        return convertView;
    }
}
