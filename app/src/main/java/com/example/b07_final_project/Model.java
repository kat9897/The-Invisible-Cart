package com.example.b07_final_project;

import java.util.ArrayList;

public interface Model {

    // in general, return null if no such object exists

    Customer getCustomer(String email);
    Owner getOwner(String email);
    Store getStore(String storeName);
    Product_ getProduct(int ID, String storeName);
    Order getOrder(int ID, String storeName);

    Store getStore(Owner owner);
    ArrayList<Product_> getProducts(Store store);
    ArrayList<Product_> getProducts(Order order);
    ArrayList<Order> getOrders(Store store);
    ArrayList<Order> getOrders(Customer customer);

    ArrayList<Store> getStores(); // returns all stores

    void saveStore(Store store);

    void saveOwner(Owner owner, String storeName);
    // note: this should replace any existing owner

    void saveCustomer(Customer customer);

    int saveOrder(Order order, String storeName);
    // method ignores order.orderID passed to it and returns the orderID order is saved with
    // note: this should also add the order to the customer's order list
    // customer's order list must contain orderID and storeName for the order

    void setOrderStatus(Order order, int status);

    void saveProduct(Product_ product, String storeName);
    void removeProduct(Product_ product, String storeName);
    // does not need to remove references to this product elsewhere in the database

    void saveProductToOrder(int productID, int quantity, Order order);
    // if quantity is 0, remove from order
}