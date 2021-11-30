package com.example.b07_final_project;

import org.junit.Test;

import static org.junit.Assert.*;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class FirebaseModelJUnit {

    @Test
    public void testGetCustomerOne(){
            FirebaseModel model = new FirebaseModel();

            Customer customer = model.getCustomer("test1@gmail.com");

            assertEquals(customer.email, "test1@gmail.com");
    }
}
