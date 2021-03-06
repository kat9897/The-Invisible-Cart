package com.example.b07_final_project.helper;

public class Owners extends Users {

    // 0 for customer
    // 1 for Owner
    private int userType = 1;

    private String orderId;
    private String productId;

    public Owners( String uid, String name, String phoneNumber, String email, String password) {
        super( uid, name, phoneNumber, email, password);
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }
}
