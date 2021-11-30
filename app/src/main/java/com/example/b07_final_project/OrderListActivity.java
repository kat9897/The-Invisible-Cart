package com.example.b07_final_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class OrderListActivity extends AppCompatActivity {
    // initializing everything :
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);
        // Hide TitleBar
        getSupportActionBar().hide();

        //Get Owner ID
        Intent intent = getIntent();
        String uid = intent.getExtras().getString("Ownerid");

        //TODO  -->
        // go to database -> Owner -> uid -> List of Orders -> Order ID
        // get the order IDs and save them in arraylist
        // then present these order IDs to ListView on the app

        listView = (ListView) findViewById(R.id.OrderList);
        ArrayList<String> orderIdList = new ArrayList<String>();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item, orderIdList);
        listView.setAdapter(adapter);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Test_Stores");
        ref.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                orderIdList.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    // get the order ID of each order
                    String order_id = ds.child("Walmart").child("Orders").child("Order_Id").getValue(String.class);
                    // store order ID to the array list of IDs
                    orderIdList.add(order_id);
                }
                // now the orderIdList should be filled with order IDs
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                // get the order ID
                String clickedOrderId = listView.getAdapter().getItem(position).toString();

                // get an Order reference to that Order ID (to access Customer Email and Products shopped)
                // Order currentOrder = getOrder(clickedOrderId);

                Intent intent = new Intent(getApplicationContext(), OrderPage.class);
                intent.putExtra("orderHeading", clickedOrderId);
                // intent.putExtra("customerName", currentOrder.getCustomer().getEmail());
                // intent.putExtra("totalPrice", singleton.orderTotal());
                startActivity(intent);
            }
        });
    }
}

    // Function to get a copy of the Order given an Order ID
//    public Order getOrder(String orderId) {
//        Order copyOrder = new Order();
//        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Test_Stores");
//        ref.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
//            @Override
//            public void onDataChange(final DataSnapshot dataSnapshot) {
//                // iterate through each store
//                for (DataSnapshot ds : dataSnapshot.getChildren()) {
//                    // iterate through each store's orders to find the order id
//                    DatabaseReference ref2 = ref.child("Orders");
//                    ref2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot snapshot) {
//                            for (DataSnapshot ds2 : dataSnapshot.getChildren()) {
//                                String currentOrderId = ds2.child("Order_Id").getValue(String.class);
//                                if (orderId.equals(currentOrderId)) {
//                                    // FOUND THE ID!
//                                    // Copy all the data
//                                    copyOrder.setOrderId(orderId);
//                                    copyOrder.setCustomer(ds2.child("Customer").getValue(Customers.class));
//                                    copyOrder.setProducts(ds2.child("Products").getValue(Product.class));
//                                    copyOrder.setStatus(ds2.child("Status").getValue(Integer.class));
//                                }
//                            }
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError error) {
//                        }
//                    });
//                }
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//            });
//        return copyOrder;
//    }
//}