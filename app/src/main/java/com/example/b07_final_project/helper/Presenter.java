package com.example.b07_final_project.helper;

import android.view.View;

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
    // newOwner also creates a new store

    Product_ newProduct(String pdtName, Double pdtPrice, String pdtBrand);

    Owner getLoggedInOwner();

    ArrayList<Store> allStores();

    ArrayList<Order_> allCustomerOrders(Customer customer);
    ArrayList<Order_> allCustomerOrders(); // assumes logged in customer

    Store getStore(Owner owner);

    ArrayList<Order_> getOrders(Owner owner); // Gets orders for the store, not the owner

    void viewOrder(Order_ order);

    Order_ getViewedOrder();

    void setQuantity(Order_ order, Product_ product, int quantity);
    int getQuantity(Order_ order, Product_ product);
    Customer getCustomer(Order_ order);

    ArrayList<Product_> getProducts(Order_ order);

    ArrayList<Product_> getProducts(Owner owner);



    // Order getViewedOrder();
    //Customer getCustomer(Order_ order);
    //ArrayList<Product_> getProducts(Order_ order);

    /*

    void Update(); // to be called exclusively by the Model (database) when the database changes

    void addView(View view); // please call this in the onResume() method of every View
    void removeView(View view); // please call this in the onPause() method of every View
    // the parameter should be "this"
    // please also reload any database items displayed during the onResume() method of every View

    Boolean ownerAccountExists(String email);
    Boolean customerAccountExists(String email);

    int getCurrentLoginType(); // returns 0 for no current login, 1 for owner logged in, 2 for customer logged in
    String getCurrentLoginEmail(); // returns email of currently logged in account

    int loginOwner(String email, String password);
    int loginCustomer(String email, String password);
    // returns 0 if login successful, 1 if account not found, 2 if wrong password, 3 for any other failure to login

    int logout();
    // returns 0 if account logged out of, 1 if no account logged in, 2 for other failure to logout

    int createOwnerAccountAndStore(String ownerEmail, String ownerName, String password, String phoneNumber, String storeName);
    int createOwnerAccountAndStore(String ownerEmail, String ownerName, String password, String phoneNumber);
    // default store name if not specified is "[ownerName]'s Store". Store name cannot be updated later.
    // if account is created also logs you into that account
    // int return value is 0 if account created successfully, 1 if not, 2 if store name is already taken

    int updateOwnerAccount(String ownerEmail, String ownerName, String password, String phoneNumber);
    // acts on the currently logged in owner
    // int return value is 0 if account updated successfully, 1 if not

    int createCustomerAccount(String email, String name, String password);
    // if account is created also logs you into that account
    // int return value is 0 if account created successfully, 1 if not

    String getEmail(); // works for owners or customers
    String getName(); // works for owners or customers
    String getOwnerPhoneNumber();
    String getOwnerStoreName();
    // returns details from on the currently logged in account

    String viewStore(String storeName);
    // sets what store a customer currently viewing
    // sets customer to not be viewing an order
    // use with empty string to view no store
    // returns the name of the store now being viewed
    String getCurrentStore();
    // returns the name of the store now being viewed by a customer

    int viewOrder(int orderID);
    // sets what order a customer or owner is currently viewing
    // use with value -1 to view no order
    // returns the ID of the order now being viewed, or -1 if it can't
    // can only view order in the logged in owner's store or the customer's currently viewed store
    int getCurrentOrder();
    // returns the ID of the order now being viewed

    ArrayList<Integer> getProducts(String storeName);
    ArrayList<Integer> getProducts(int orderID);
    ArrayList<Integer> getProducts();
    // returns a list of products IDs in the store or order specified
    // if no orderID or storeName specified:
    //    if owner logged in, returns IDs for logged in owner's store's products
    //    if customer logged in, returns IDs for products of the store the customer is viewing

    String getProductName(int productID);
    String getProductBrand(int productID);
    float getProductPrice(int productID);
    // returns info on products
    // if owner is logged in, pulls products from the owner's store
    // if customer is logged in, pulls products from the current store customer is viewing

    int addProduct(String productName, String productBrand, float productPrice);
    // adds a new product to the logged in owner's store
    // returns ID of new product if added successfully, or -1 if product wasn't added
    int updateProduct(int productID, String productName, String productBrand, float productPrice);
    // updates a product on the logged in owner's store
    // productID identifies product, and does not change
    // returns 0 if updated successfully, 1 otherwise
    int addProduct(int productID, int quantity);
    // adds a new product to currently viewed order for logged in customer
    // if product already exists in order, updates quantity of product in that order
    // returns 0 if added successfully, 1 otherwise

    ArrayList<Integer> getOrders();
    // if owner is logged in, returns a list of IDs of orders for the store
    // if customer is logged in, returns a list of IDs of orders the customer has placed

    int updateOrderStatus(int status, int orderID);
    int updateOrderStatus(int status);
    // updates status of an order at the logged in owner's store
    // defaults to currently viewed order if no ID specified
    // returns 0 if updated successfully (including no change in status), or 1 if not

    ArrayList<String> getStores();
    // returns a list of the names of all stores on the app

    int createOrder();
    // returns the ID of a new order for the logged in customer at the store they're currently viewing
    // also sets the new order as the currently viewed order
*/
}

