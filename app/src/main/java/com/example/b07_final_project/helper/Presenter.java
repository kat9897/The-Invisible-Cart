package com.example.b07_final_project.helper;


import java.util.ArrayList;

public interface Presenter {

    int save(IDobj obj);

    Customer loginCustomer(String email, String password);
    Owner loginOwner(String email, String password);

    Boolean customerExists(String email);
    Boolean ownerExists(String email);
    boolean storeExists(String storename); // Added by Yashank

    Customer newCustomer(String email, String name, String password);
    Owner newOwner(String email, String name, String password, String phoneNumber, String storename);
    // newOwner also creates a new store and also logs you in
    Order_ newOrder(Customer customer, Store store); // changes currentOrder to this order
    Product_ newProduct(String pdtName, Double pdtPrice, String pdtBrand);

    Owner getLoggedInOwner();
    Customer getLoggedInCustomer();

    ArrayList<Store> allStores();
    ArrayList<Order_> allCustomerOrders(Customer customer);
    ArrayList<Order_> allCustomerOrders(); // assumes logged in customer

    void viewOrder(Order_ order);
    Order_ getViewedOrder();
    Store getViewedStore();

    void setQuantity(Order_ order, Product_ product, int quantity);
    int getQuantity(Order_ order, Product_ product);

    void addProductToOrder(Order_ order, String product_id, int quantity);

    Customer getCustomer(Order_ order);
    Store getStore(Owner owner);
    Store getStore(Order_ order);
        ArrayList<Order_> getOrders(Owner owner); // Gets orders for the store, not the owner
    ArrayList<Order_> getOrders(Customer customer);
    ArrayList<Product_> getProducts(Order_ order);
    ArrayList<Product_> getProducts(Owner owner);
    ArrayList<Product_> getProducts(Store store);

    void viewStore(Store store);

    void setCurrentLogin(IDobj newLogin);

}

