package com.example.b07_final_project;

import java.util.ArrayList;

public interface Model {

    Customer getCustomer(String email);
    Owner getOwner(String email);
    Store getStore(String storeName);
    Product_ getProduct(int ID, String storeName);
    Order getOrder(int ID, String storeName);

    Owner getOwner(Store store);
    Store getStore(Owner owner);
    ArrayList<Product_> getProducts(Store store);
    ArrayList<Order> getOrders(Store store);
    ArrayList<Order> getOrders(Customer customer);

    ArrayList<Store> getStores(); // returns all stores
    ArrayList<Customer> getCustomers(); // returns all customers

    void saveStore(Store store);
    void removeStore(String storeName);

    void saveOwner(Owner owner, String storeName);
    // note: this should replace any existing owner

    void saveCustomer(Customer customer);
    void removeCustomer(String customerEmail);

    void saveOrder(Order order, String storeName);
    void removeOrder(int orderID, String storeName);
    // note: these 2 should also add/remove the order from the customer's order list
    // customer's order list must contain orderID and storeName for the order

    void saveProduct(Product product, String storeName);
    void removeProduct(Product product, String storeName);

}