package com.example.b07_final_project.helper;

import android.content.Intent;

import com.example.b07_final_project.owner_dashboard.Add_New_Product;
import com.example.b07_final_project.owner_dashboard.My_Products;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class Singleton implements Presenter {

    // Access to Singleton

    private static Presenter ID;

    public static Presenter getID() {
        if (ID == null)
            ID = new Singleton();
        return ID;
    }

    private final Model database;

    private Singleton() {
        database = new FirebaseModel();
    }

    // project state variables

    private IDobj currentLogin = null;

    // methods

    @Override
    public int save(IDobj obj) {
        database.saveIDobj(obj);
        return 0;
    }

    @Override
    public Customer loginCustomer(String email, String password) {

        ArrayList<IDobj> customers = database.getAllIDobj(IDobj.CUSTOMER);

        for (IDobj o : customers){
            Customer c = (Customer) o;

            if (c.getEmail().equals(email)){
                if (c.getPassword().equals(password)) {
                    currentLogin = database.getIDobj(c); // get a new one so it's not same as return
                    return c;
                } else
                    return null;
            }
        }
        return null;
    }

    @Override
    public Owner loginOwner(String email, String password) {

        ArrayList<IDobj> owners = database.getAllIDobj(IDobj.OWNER);

        for (IDobj o : owners){
            Owner owner = (Owner) o;

            if (owner.getEmail().equals(email)){
                if (owner.getPassword().equals(password)) {
                    currentLogin = database.getIDobj(owner); // get a new one so it's not same as return
                    return owner;
                } else
                    return null;
            }
        }
        return null;
    }

    @Override
    public Boolean customerExists(String email){

        ArrayList<IDobj> customers = database.getAllIDobj(IDobj.CUSTOMER);

        for (IDobj o : customers){
            Customer c = (Customer) o;

            if (c.getEmail().equals(email))
                return true;
        }
        return false;
    }

    @Override
    public Boolean ownerExists(String email){

        ArrayList<IDobj> owners = database.getAllIDobj(IDobj.OWNER);

        for (IDobj o : owners){
            Owner owner = (Owner) o;

            if (owner.getEmail().equals(email))
                return true;
        }
        return false;
    }

    @Override
    public Customer newCustomer(String email, String name, String password) {

        Customer customer = (Customer) database.newIDobj(IDobj.CUSTOMER);

        customer.setEmail(email);
        customer.setName(name);
        customer.setPassword(password);

        customer.save();

        return customer;
    }

    @Override
    public Owner newOwner(String email, String name, String password, String phoneNumber) {

        Owner owner = (Owner) database.newIDobj(IDobj.OWNER);
        Store store = (Store) database.newIDobj(IDobj.STORE);

        database.addRelation(owner, store);

        owner.setEmail(email);
        owner.setPassword(password);
        owner.setName(name);
        owner.setPhoneNumber(phoneNumber);

        owner.save();

        store.setName("Default");

        store.save();

        return owner;
    }

    @Override
    public Owner getLoggedInOwner(){
        if(currentLogin.getType() == IDobj.OWNER){
            return (Owner) currentLogin;
        }

        return null;
    }

    @Override
    public Store getStore(Owner owner){
            ArrayList<IDobj> owners_store = database.getRelations(owner, IDobj.STORE);

            IDobj store = owners_store.get(0);

            return (Store) store;




    }





    @Override
    public Product_ newProduct(String pdtName, Double pdtPrice, String pdtBrand) {

        Product_ product = (Product_) database.newIDobj(IDobj.PRODUCT);
        //Store store = getStore(currentLogin);
        //database.addRelation(product, store);

        product.setName(pdtName);
        product.setPrice(pdtPrice);
        product.setBrand(pdtBrand);

        product.save();
        //store.save();

        return product;
    }













}
















/*

public class Singleton implements Presenter {

    // Access to Singleton in section below

    static Presenter ID;

    static Presenter getID() {
        if (ID == null)
            ID = new Singleton();
        return ID;
    }

    private final Model database;

    private Singleton() {
        database = new FirebaseModel();
    }

    // Internal information handling for Singleton below

    private final ArrayList<View> activeViews = new ArrayList<>();

    private final int NONE = 0, OWNER = 1, CUSTOMER = 2;

    private int currentLogin = NONE; // should only be non-NONE if currentOwner/currentCustomer is non-null
    private Owner currentOwner = null;
    private Customer currentCustomer = null;

    private Store viewedStore = null; // should always exist if being viewed
    private Order viewedOrder = null; // should always exist if being viewed
    // if called by an owner, viewedOrder is the ID of the order at the store
    // if called by a customer, viewedOrder is the index of the order in the customer order list

    private static final String EMAILREGEX = "[a-z0-9][\\w]*@[a-z0-9][a-z0-9]*\\.[a-z][a-z]*"; // could be refined further
    private static final String NAMEREGEX = "[\\w][ '\\w]*";
    private static final String PASSWORDREGEX = ".*";
    private static final String PHONEREGEX = "[- \\d]*"; // could be refined further

    private final int DEFAULTORDERSTATUS = 0;

    // Presenter method overrides section below

    @Override
    public void Update() { // to be called exclusively by the Model (database) when the database changes
        for (View view : activeViews)
            view.update();
    }

    @Override
    public void addView(View view) {
        activeViews.add(view);
    }
    @Override
    public void removeView(View view) {
        activeViews.remove(view); // relies on the View implementing reference equality for its equals method (which is the default)
    }

    @Override
    public Boolean ownerAccountExists(String email){
        email = email.toLowerCase();
        if (database.getOwner(email) == null)
            return false;
        return true;
    }
    @Override
    public Boolean customerAccountExists(String email){
        email = email.toLowerCase();
        if (database.getCustomer(email) == null)
            return false;
        return true;
    }

    @Override
    public int getCurrentLoginType() { // returns 0 for no current login, 1 for owner logged in, 2 for customer logged in
        switch(currentLogin) {
            case OWNER:
                return 1;
            case CUSTOMER:
                return 2;
        }
        return 0;
    }
    @Override
    public String getCurrentLoginEmail() { // returns email of currently logged in account
        String email = null;

        switch(currentLogin) {
            case NONE:
                break;
            case OWNER:
                email = currentOwner.email;
                break;
            case CUSTOMER:
                email = currentCustomer.email;
                break;
        }

        if (email == null)
            return "";
        return email;
    }

    @Override
    public int loginOwner(String email, String password){
        email = email.toLowerCase();
        Owner o = database.getOwner(email);
        if (o == null)
            return 1;
        if (!(o.password.equals(password)))
            return 2;

        currentLogin = OWNER;
        currentOwner = o;
        return 0;
    }
    @Override
    public int loginCustomer(String email, String password){
        email = email.toLowerCase();
        Customer c = database.getCustomer(email);
        if (c == null)
            return 1;
        if (!(c.password.equals(password)))
            return 2;

        currentLogin = CUSTOMER;
        currentCustomer = c;
        return 0;
    }
    // returns 0 if login successful, 1 if account not found, 2 if wrong password, 3 for any other failure to login

    @Override
    public int logout(){
        if (currentLogin == NONE)
            return 1;
        currentLogin = NONE;
        return 0;
    }
    // returns 0 if account logged out of, 1 if no account logged in, 2 for other failure to logout

    @Override
    public int createOwnerAccountAndStore(String ownerEmail, String ownerName, String password, String phoneNumber, String storeName) {
        ownerEmail = ownerEmail.toLowerCase();

        if (database.getStore(storeName) != null)
            return 2; // store name already taken

        if (ownerAccountExists(ownerEmail))
            return 1; // owner email already taken

        if (!(Pattern.matches(EMAILREGEX, ownerEmail)
                && Pattern.matches(NAMEREGEX, ownerName)
                && Pattern.matches(PASSWORDREGEX, password)
                && Pattern.matches(PHONEREGEX, phoneNumber)
                && Pattern.matches(NAMEREGEX, storeName)))
            return 1; // incorrect format for at least 1 input

        Store s = new Store();
        s.storeName = storeName;
        Owner o = new Owner();
        o.email = ownerEmail;
        o.name = ownerName;
        o.password = password;
        o.phoneNumber = phoneNumber;

        database.saveStore(s);
        database.saveOwner(o, storeName);

        loginOwner(ownerEmail, password);
        return 0;
    }
    @Override
    public int createOwnerAccountAndStore(String ownerEmail, String ownerName, String password, String phoneNumber) {
        return createOwnerAccountAndStore(ownerEmail, ownerName, password, phoneNumber, ownerName + "'s Store");
    }
    // default store name if not specified is "[ownerName]'s Store". Can Be Updated later.
    // if account is created also logs you into that account
    // int return value is 0 if account created successfully, 1 if not, 2 if store name is already taken,

    @Override
    public int updateOwnerAccount(String ownerEmail, String ownerName, String password, String phoneNumber) {
        ownerEmail = ownerEmail.toLowerCase();

        if (currentLogin != OWNER)
            return 1;

        if (ownerAccountExists(ownerEmail))
            if (currentOwner.email.equals(ownerEmail))
                return 1; // owner email already taken

        if (!(Pattern.matches(EMAILREGEX, ownerEmail)
                && Pattern.matches(NAMEREGEX, ownerName)
                && Pattern.matches(PASSWORDREGEX, password)
                && Pattern.matches(PHONEREGEX, phoneNumber)))
            return 1; // incorrect format for at least 1 input

        Owner o = new Owner();
        o.email = ownerEmail;
        o.name = ownerName;
        o.password = password;
        o.phoneNumber = phoneNumber;

        database.saveOwner(o, database.getStore(currentOwner).storeName);

        loginOwner(ownerEmail, password);
        return 0;
    }
    // acts on the currently logged in owner
    // int return value is 0 if account updated successfully, 1 if not

    @Override
    public int createCustomerAccount(String email, String name, String password){
        email = email.toLowerCase();

        if (database.getCustomer(email) != null)
            return 1; // email address already taken

        if (!(Pattern.matches(EMAILREGEX, email)
                && Pattern.matches(NAMEREGEX, name)
                && Pattern.matches(PASSWORDREGEX, password)))
            return 1; // incorrect format for at least 1 input

        Customer c = new Customer();
        c.email = email;
        c.name = name;
        c.password = password;

        database.saveCustomer(c);
        loginCustomer(email, password);
        return 0;
    }
    // if account is created also logs you into that account
    // int return value is 0 if account created successfully, 1 if not

    @Override
    public String getEmail() { // works for owners or customers
        switch (currentLogin) {
            case CUSTOMER:
                return currentCustomer.email;
            case OWNER:
                return currentOwner.email;
        }
        return  "";
    }
    @Override
    public String getName() { // works for owners or customers
        switch (currentLogin) {
            case CUSTOMER:
                return currentCustomer.name;
            case OWNER:
                return currentOwner.name;
        }
        return  "";
    }
    @Override
    public String getOwnerPhoneNumber() {
        if (currentLogin == OWNER)
            return currentOwner.phoneNumber;
        return "";
    }
    @Override
    public String getOwnerStoreName() {
        if (currentLogin == OWNER)
            return database.getStore(currentOwner).storeName;
        return "";
    }
    // returns details from on the currently logged in account

    @Override
    public String viewStore(String storeName) {

        if (currentLogin != CUSTOMER)
            return null; // this method only for customers

        viewedStore = database.getStore(storeName);
        viewedOrder = null;

        return getCurrentStore();
    }
    // sets what store a customer currently viewing
    // sets customer to not be viewing an order
    // use with empty string to view no store
    // returns the name of the store now being viewed
    @Override
    public String getCurrentStore() {

        if (currentLogin != CUSTOMER)
            return null; // this method only for customers

        if (viewedStore == null)
            return "";

        return viewedStore.storeName;
    }
    // returns the name of the store now being viewed by a customer

    @Override
    public int viewOrder(int orderID) {
        // if called by an owner, orderID is the ID of the order at the store
        // if called by a customer, orderID is the index of the order in the customer order list

        if (!(currentLogin == OWNER || (currentLogin == CUSTOMER && viewedStore != null)))
            return -1; // not an acceptable case to call this method

        if (orderID == -1) {
            viewedOrder = null;
            return -1; // now viewing no order
        }

        if (currentLogin == OWNER) {
            viewedStore = database.getStore(currentOwner);
            viewedOrder = database.getOrder(orderID, database.getStore(currentOwner).storeName);
            if (viewedOrder == null)
                return -1;
            return viewedOrder.orderID;
        }

        // currentLogin == CUSTOMER to reach this point

        ArrayList<Order> orders = database.getOrders(currentCustomer);
        if (orderID >= orders.size() || orderID < 0)
            return -1; // invalid index
        Order o = orders.get(orderID);

        if (!(viewedStore.storeName.equals(o.storeName))) {
            viewedOrder = null;
            return -1; // order not in viewed store
        }

        viewedOrder = database.getOrder(o.orderID,o.storeName);
        return orders.indexOf(o);
    }
    // sets what order a customer or owner is currently viewing
    // use with value -1 to view no order
    // returns the ID of the order now being viewed, or -1 if it can't
    // can only view order in the logged in owner's store or the customer's currently viewed store
    @Override
    public int getCurrentOrder() {

        if (!(currentLogin == OWNER || (currentLogin == CUSTOMER && viewedStore != null)))
            return -1; // not an acceptable case to call this method

        if (viewedOrder == null)
            return -1;

        switch(currentLogin) {
            case OWNER:

                return viewedOrder.orderID;

            case CUSTOMER:

                ArrayList<Order> orders = database.getOrders(currentCustomer);

                for (Order o : orders)
                    if (o.storeName.equals(viewedOrder.storeName) && o.orderID == viewedOrder.orderID)
                        return orders.indexOf(o);

        }

        return -1;
    }
    // returns the ID of the order now being viewed

    @Override
    public ArrayList<Integer> getProducts(String storeName) {

        Store s = database.getStore(storeName);
        ArrayList<Product_> products = database.getProducts(s);

        ArrayList<Integer> output = new ArrayList<>();
        for (Product_ p : products)
            output.add(p.productID);

        return output;
    }
    @Override
    public ArrayList<Integer> getProducts(int orderID) {

        Order o;

        switch (currentLogin) {
            case OWNER:
                o = database.getOrder(orderID, database.getStore(currentOwner).storeName);
                break;
            case CUSTOMER:
                ArrayList<Order> orders = database.getOrders(currentCustomer);
                o = orders.get(orderID);
                break;
            default:
                return null; // not logged in
        }

        ArrayList<Product_> products = database.getProducts(o);

        ArrayList<Integer> output = new ArrayList<>();
        for (Product_ p : products)
            output.add(p.productID);

        return output;
    }
    @Override
    public ArrayList<Integer> getProducts() {
        switch (currentLogin) {
            case OWNER:
                return getProducts(database.getStore(currentOwner).storeName);
            case CUSTOMER:
                if (viewedStore == null)
                    return null;
                return getProducts(viewedStore.storeName);
        }
        return null;
    }
    // returns a list of products IDs in the store or order specified
    // if no orderID or storeName specified:
    //    if owner logged in, returns IDs for logged in owner's store's products
    //    if customer logged in, returns IDs for products of the store the customer is viewing

    @Override
    public String getProductName(int productID) {
        switch (currentLogin) {
            case OWNER:
                return database.getProduct(productID, database.getStore(currentOwner).storeName).name;
            case CUSTOMER:
                if (viewedStore == null)
                    return null;
                return database.getProduct(productID, viewedStore.storeName).name;
        }
        return "";
    }
    @Override
    public String getProductBrand(int productID) {
        switch (currentLogin) {
            case OWNER:
                return database.getProduct(productID, database.getStore(currentOwner).storeName).brand;
            case CUSTOMER:
                if (viewedStore == null)
                    return null;
                return database.getProduct(productID, viewedStore.storeName).brand;
        }
        return "";
    }
    @Override
    public float getProductPrice(int productID) {
        switch (currentLogin) {
            case OWNER:
                return database.getProduct(productID, database.getStore(currentOwner).storeName).price;
            case CUSTOMER:
                if (viewedStore == null)
                    return -1; // customer not viewing a store
                return database.getProduct(productID, viewedStore.storeName).price;
        }
        return -1; // not logged in
    }
    // returns info on products
    // if owner is logged in, pulls products from the owner's store
    // if customer is logged in, pulls products from the current store customer is viewing

    @Override
    public int addProduct(String productName, String productBrand, float productPrice) {

        if (currentLogin != OWNER)
            return -1; // not logged in

        if (!(Pattern.matches(NAMEREGEX, productName.toLowerCase())
                && Pattern.matches(NAMEREGEX, productBrand.toLowerCase())
                && productPrice >= 0.0))
            return -1; // incorrect format for at least 1 input

        Product_ p = new Product_();
        p.name = productName;
        p.brand = productBrand;
        p.price = productPrice;

        // assign a product ID
        ArrayList<Product_> products = database.getProducts(database.getStore(currentOwner));
        for(int i = 1; true; i++) {
            boolean used = false;
            for (Product_ product : products) {
                if (product.productID == i) {
                    used = true;
                    break;
                }
            }
            if (!used) {
                p.productID = i;
                break;
            }
        }

        int i = p.productID;
        database.saveProduct(p, database.getStore(currentOwner).storeName);
        return i;
    }
    // adds a new product to the logged in owner's store
    // returns ID of new product if added successfully, or -1 if product wasn't added
    @Override
    public int updateProduct(int productID, String productName, String productBrand, float productPrice) {

        if (currentLogin != OWNER)
            return 1; // not logged in

        if (!(Pattern.matches(NAMEREGEX, productName.toLowerCase())
                && Pattern.matches(NAMEREGEX, productBrand.toLowerCase())
                && productPrice >= 0.0))
            return 1; // incorrect format for at least 1 input

        Product_ p = database.getProduct(productID, database.getStore(currentOwner).storeName);
        Product_ q = database.getProduct(productID, database.getStore(currentOwner).storeName);

        p.name = productName;
        p.brand = productBrand;
        p.price = productPrice;

        database.removeProduct(q, database.getStore(currentOwner).storeName);
        database.saveProduct(p, database.getStore(currentOwner).storeName);

        return 0;
    }
    // updates a product on the logged in owner's store
    // productID identifies product, and does not change
    // returns 0 if updated successfully, 1 otherwise
    @Override
    public int addProduct(int productID, int quantity) {

        if (currentLogin != CUSTOMER)
            return 1; // not logged in

        if (quantity < 0)
            return 1; // impossible quantity

        if (viewedOrder == null)
            return 1; // not viewing an order

        Store s = database.getStore(viewedOrder.storeName);
        Product_ p = database.getProduct(productID, s.storeName);

        if (p == null)
            return 1; // product of this ID does not exist

        database.saveProductToOrder(productID, quantity, viewedOrder);
        return 0;
    }
    // adds a new product to currently viewed order for logged in customer
    // returns 0 if added successfully, 1 otherwise

    @Override
    public ArrayList<Integer> getOrders() {

        switch (currentLogin) {
            case OWNER:

                ArrayList<Order> orders = database.getOrders(database.getStore(currentOwner));
                ArrayList<Integer> output1 = new ArrayList<>();

                for (Order o : orders)
                    output1.add(o.orderID);

                return output1;
            case CUSTOMER:

                int size = database.getOrders(currentCustomer).size();
                ArrayList<Integer> output2 = new ArrayList<>();

                for (int i = 0; i < size; i++)
                    output2.add(i);

                return output2;
        }

        return null; // not logged in
    }
    // if owner is logged in, returns a list of IDs of orders for the store
    // if customer is logged in, returns a list of IDs of orders the customer has placed

    @Override
    public int updateOrderStatus(int status, int orderID) {

        if (currentLogin != OWNER)
            return 1; // not logged in

        Order o = database.getOrder(orderID, database.getStore(currentOwner).storeName);

        if (o == null)
            return 1; // order does not exist

        database.setOrderStatus(o, status);
        return 0;
    }
    @Override
    public int updateOrderStatus(int status) {

        if (currentLogin != OWNER)
            return 1; // not logged in

        if (viewedOrder == null)
            return 1; // not viewing an order

        return updateOrderStatus(status, viewedOrder.orderID);
    }
    // updates status of an order at the logged in owner's store
    // defaults to currently viewed order if no ID specified
    // returns 0 if updated successfully (including no change in status), or 1 if not

    @Override
    public ArrayList<String> getStores() {

        ArrayList<Store> stores = database.getStores();
        ArrayList<String> output = new ArrayList<>();

        for (Store s : stores)
            output.add(s.storeName);

        return output;
    }
    // returns a list of the names of all stores on the app

    @Override
    public int createOrder() {

        if (currentLogin != CUSTOMER)
            return 1; // not logged in

        if (viewedStore == null)
            return 1; // not viewing a store

        Order o = new Order();
        o.status = DEFAULTORDERSTATUS;
        o.storeName = viewedStore.storeName;
        o.customerEmail = getEmail();

        o.orderID = database.saveOrder(o, viewedStore.storeName);

        viewedOrder = o;
        return o.orderID;
    }
    // returns the ID of a new order for the logged in customer at the store they're currently viewing
    // also sets the new order as the currently viewed order

}

*/
