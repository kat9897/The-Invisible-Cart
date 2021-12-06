package com.example.b07_final_project.helper;

public interface LoginPresenterInterface {

    Customer loginCustomer(String email, String password);

    Owner loginOwner(String email, String password);

    Boolean customerExists(String email);

    Boolean ownerExists(String email);

    boolean storeExists(String storeName); // Added by Yashank

    Customer newCustomer(String email, String name, String password);

    // also logs you in
    Owner newOwner(String email, String name, String password, String phoneNumber, String storeName);
    // also logs you in
    // also creates a new store

    void customerLoginClicked(MVPview view, String email, String password);

    void ownerLoginClicked(MVPview view, String email, String password);

    void customerSignupClicked(MVPview thisActivity, String name, String email, String password, String confirmPassword);

    void ownerSignupClicked(MVPview view, String name, String email, String password, String confirmPassword, String phoneNumber, String storeName);

    String displayMessage(String message);

}
