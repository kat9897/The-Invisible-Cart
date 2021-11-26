package com.example.b07_final_project;

import java.util.ArrayList;

public interface Presenter {

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
    // default store name if not specified is "[ownerName]'s Store". Can Be Updated later.
    // if account is created also logs you into that account
    // int return value is 0 if account created successfully, 1 if not

    int updateOwnerAccount(String ownerEmail, String ownerName, String password, String phoneNumber);
    int updateStoreName(String storeName);
    int updateOwnersStoreName(String ownerEmail); // purely an alternative to updateStoreName if it's easier
    // acts on the currently logged in owner
    // int return value is 0 if account updated successfully, 1 if not

    int createCustomerAccount(String email, String name, String password);
    // if account is created also logs you into that account
    // int return value is 0 if account created successfully, 1 if not

    int updateCustomerAccount(String email, String name, String password);
    // acts on the currently logged in customer
    // int return value is 0 if account updated successfully, 1 if not

    String getEmail(); // works for owners or customers
    String getName(); // works for owners or customers
    String getOwnerPhoneNumber();
    String getOwnerStoreName();
    // returns details from on the currently logged in account

    String viewStore(String storeName);
    // sets what store a customer currently viewing
    // use with empty string to view no store
    // returns the name of the store now being viewed
    String getCurrentStore();
    // returns the name of the store now being viewed by a customer

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
    // returns 0 if updated successfully, 1 otherwise
    int removeProduct(int productID);
    // removes a product from the logged in owner's store
    // returns 0 if removed successfully, 1 otherwise
    int addProduct(int productID, int quantity, int orderID);
    // adds a new product to specified order
    // returns 0 if added successfully, 1 otherwise
    int updateProduct(int productID, int quantity, int orderID);
    // updates quantity of a product in specified order
    // update to 0 to remove the product from the order
    // returns 0 if updated successfully, 1 otherwise

    ArrayList<Integer> getOrders();
    // if owner is logged in, returns a list of IDs of orders for the store
    // if customer is logged in, returns a list of IDs of orders the customer has placed

    int UpdateOrderStatus(int orderID, int status);
    // updates status of an order at the logged in owner's store
    // returns 0 if updated successfully (including no change in status), or 1 if not

    ArrayList<String> getStores();
    // returns a list of the names of all stores on the app

    int createOrder();
    // returns the ID of a new order for the logged in customer at the store they're currently viewing
    int deleteOrder(int orderID);
    // deletes the specified order for the logged in customer

}

