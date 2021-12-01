package com.example.b07_final_project.helper;

import java.util.ArrayList;

public interface Model {

    IDobj newIDobj(int type);
    // creates object in database
    // prereq: none

    void saveIDobj(IDobj obj);
    // prereq: object exists in database (deleted objects can't be saved)

    IDobj getIDobj(int type, String ID);
    IDobj getIDobj(IDobj obj);
    ArrayList<IDobj> getAllIDobj(int type);

    void addRelation(IDobj obj1, IDobj obj2); // adds nothing if relation already exists
    ArrayList<IDobj> getRelations(IDobj obj, int type);

    Boolean relationExists(IDobj obj1, IDobj obj2);
    void setRelationContext(IDobj obj1, IDobj obj2, String context);
    String getRelationContext(IDobj obj1, IDobj obj2);
    // unlike relations, relation contexts are 1-directional
    // from "obj1" to "obj2"
    // a relation must already exist before attaching context
    // pass "null" as the string to remove context


    void deleteIDobj (IDobj obj);




    /*

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




     */
}