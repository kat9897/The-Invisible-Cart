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
    Order newOrder(Customer customer, Store store); // changes currentOrder to this order
    Product newProduct(String pdtName, Double pdtPrice, String pdtBrand);

    Owner getLoggedInOwner();
    Customer getLoggedInCustomer();

    ArrayList<Store> allStores();
    ArrayList<Order> allCustomerOrders(Customer customer);
    ArrayList<Order> allCustomerOrders(); // assumes logged in customer

    void viewOrder(Order order);
    Order getViewedOrder();
    Store getViewedStore();

    void setQuantity(Order order, Product product, int quantity);
    int getQuantity(Order order, Product product);

    void addProductToOrder(Order order, String product_id, int quantity);

    Customer getCustomer(Order order);
    Store getStore(Owner owner);
    Store getStore(Order order);
        ArrayList<Order> getOrders(Owner owner); // Gets orders for the store, not the owner
    ArrayList<Order> getOrders(Customer customer);
    ArrayList<Product> getProducts(Order order);
    ArrayList<Product> getProducts(Owner owner);
    ArrayList<Product> getProducts(Store store);

    void viewStore(Store store);

    void setCurrentLogin(IDobj newLogin);

}

