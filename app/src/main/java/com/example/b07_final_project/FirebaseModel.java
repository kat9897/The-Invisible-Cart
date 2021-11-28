
package com.example.b07_final_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Pattern;

public class FirebaseModel{

        //firebase object that will be used in the method implementations
        private FirebaseDatabase database;

        //Constructor which initializes the firebase object to a specific instance of our FirebaseDatabase
        public FirebaseModel(){
                database = FirebaseDatabase.getInstance();
        }


        //Takes in an email as input and initializes a customer object based upon
        //the customer in the database with that email.
        public Customer getCustomer(String email)
        {
                Customer customer = new Customer();

                DatabaseReference ref = database.getReference("Customer");

                ref.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                        @Override
                        public void onDataChange(final DataSnapshot dataSnapshot){
                                for(DataSnapshot ds: dataSnapshot.getChildren()){
                                        String user_email = ds.child("email").getValue(String.class);

                                        if (user_email.equals(email)){
                                                customer.email = user_email;

                                                customer.name = ds.child("name").getValue(String.class);

                                                customer.password = ds.child("password").getValue(String.class);
                                        }
                                }

                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error){

                        }
                });






                return customer;
        }



}


