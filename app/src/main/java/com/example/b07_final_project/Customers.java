package com.example.b07_final_project;

public class Customers extends Users{
    // 0 for customer
    // 1 for Owner
    private int userType = 0;
    private String orderId;
    public Customers( String uid, String name, String phoneNumber, String email, String password) {
        super( uid, name, phoneNumber, email, password);
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }
}
