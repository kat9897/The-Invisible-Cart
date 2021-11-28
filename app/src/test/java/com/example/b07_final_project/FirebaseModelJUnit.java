package com.example.b07_final_project;

import org.junit.Test;

import static org.junit.Assert.*;

public class FirebaseModelJUnit {

    @Test
    public void testGetCustomerOne(){
            FirebaseModel model = new FirebaseModel();

            Customer customer = model.getCustomer("test1@gmail.com");

            assertEquals(customer.email, "test1@gmail.com");
    }
}
