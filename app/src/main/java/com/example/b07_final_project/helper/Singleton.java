package com.example.b07_final_project.helper;

import java.util.ArrayList;

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
    private Store currentStore = null;
    private Order currentOrder = null;

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
    public boolean storeExists(String storeName) {

        ArrayList<IDobj> stores = database.getAllIDobj(IDobj.STORE);

        for (IDobj o : stores){
            Store store = (Store) o;

            if (store.getName().equals(storeName))
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

        currentLogin = customer;

        return customer;
    }

    @Override
    public Owner newOwner(String email, String name, String password, String phoneNumber, String storename) {

        Owner owner = (Owner) database.newIDobj(IDobj.OWNER);
        Store store = (Store) database.newIDobj(IDobj.STORE);

        database.addRelation(owner, store);

        owner.setEmail(email);
        owner.setPassword(password);
        owner.setName(name);
        owner.setPhoneNumber(phoneNumber);

        owner.save();

        store.setName(storename);
        store.save();

        currentLogin = owner;

        return owner;
    }

    @Override
    public Owner getLoggedInOwner(){
        if (currentLogin.getType() == IDobj.OWNER)
            return (Owner) currentLogin;
        return null;
    }

    @Override
    public Customer getLoggedInCustomer() {
        if (currentLogin.getType() == IDobj.CUSTOMER)
            return (Customer) currentLogin;
        return null;
    }

    // Get the store object of the store that is currently related to
    @Override
    public Store getStore(Owner owner){
        ArrayList<IDobj> owners_store = database.getRelations(owner, IDobj.STORE);
        IDobj store = owners_store.get(0);
        return (Store) store;
    }

    // Get store for customer order
    @Override
    public Store getStore(Order order) {
        ArrayList<IDobj> owners_store = database.getRelations(order, IDobj.STORE);
        IDobj store = owners_store.get(0);
        return (Store) store;
    }

    @Override
    public Product newProduct(String pdtName, Double pdtPrice, String pdtBrand) {

        Product product = (Product) database.newIDobj(IDobj.PRODUCT);
        Store store = getStore(getLoggedInOwner());
        database.addRelation(product, store);

        product.setName(pdtName);
        product.setPrice(pdtPrice);
        product.setBrand(pdtBrand);

        product.save();
        store.save();

        return product;
    }

    @Override
    public Order newOrder(Customer customer, Store store) {

        Order order = (Order) database.newIDobj(IDobj.ORDER);
        database.addRelation(order, customer);
        database.addRelation(order, store);

        currentOrder = order;

        order.save();

        currentOrder = order;

        return order;
    }

    @Override
    public void viewOrder(Order order){
        this.currentOrder = order;
    }

    @Override
    public void viewStore(Store store) {
        this.currentStore = store;
    }

    @Override
    public ArrayList<Store> allStores(){
        ArrayList<IDobj> idobj_List = database.getAllIDobj(IDobj.STORE);

        ArrayList<Store> store_List = new ArrayList<> ();

        for(IDobj object : idobj_List){
            store_List.add((Store)object);
        }

        return store_List;
    }

    @Override
    public Order getViewedOrder(){
        return this.currentOrder;
    }

    @Override
    public Store getViewedStore() {
        return this.currentStore;
    }

    @Override
    public Customer getCustomer(Order order){
        ArrayList<IDobj> orders_Customer = database.getRelations(order, IDobj.CUSTOMER);

        return (Customer) orders_Customer.get(0);
    }

    @Override
    public ArrayList<Product> getProducts(Order order){
        ArrayList<IDobj> idobj_list = database.getRelations(order, IDobj.PRODUCT);

        ArrayList<Product> product_List = new ArrayList<>();

        for(IDobj object : idobj_list){
            product_List.add((Product)object);
        }

        return product_List;
    }

    @Override
    public ArrayList<Product> getProducts(Owner owner){
        Store store = getStore(owner);
        ArrayList<IDobj> idobj_list = database.getRelations(store, IDobj.PRODUCT);

        ArrayList<Product> product_List = new ArrayList<>();

        for(IDobj object : idobj_list){
            product_List.add((Product)object);
        }

        return product_List;
    }

    @Override
    public ArrayList<Product> getProducts(Store store) {
        ArrayList<IDobj> idobj_list = database.getRelations(store, IDobj.PRODUCT);

        ArrayList<Product> product_List = new ArrayList<>();

        for(IDobj object : idobj_list){
            product_List.add((Product)object);
        }

        return product_List;
    }

    @Override
    public ArrayList<Order> getOrders(Owner owner){
        Store store = getStore(owner);
        ArrayList<IDobj> idobj_list = database.getRelations(store, IDobj.ORDER);

        ArrayList<Order> order_List = new ArrayList<>();

        for(IDobj object : idobj_list){
            order_List.add((Order)object);
        }

        return order_List;
    }

    @Override
    public ArrayList<Order> getOrders(Customer customer) {
        ArrayList<IDobj> idobj_list = database.getRelations(customer, IDobj.ORDER);

        ArrayList<Order> order_List = new ArrayList<>();

        for(IDobj object : idobj_list){
            order_List.add((Order)object);
        }

        return order_List;
    }


    @Override
    public ArrayList<Order> allCustomerOrders(Customer customer) {

        ArrayList<IDobj> orders = database.getRelations(customer, IDobj.ORDER);
        ArrayList<Order> output = new ArrayList<>();

        for (IDobj o : orders){
            Order order = (Order) o;
            output.add(order);
        }

        return output;
    }

    @Override
    public ArrayList<Order> allCustomerOrders() { // assumes logged in customer

        if (this.currentLogin == null)
            return null;
        if (currentLogin.getType() != IDobj.CUSTOMER)
            return null;
        // customer must be logged in at this point

        Customer customer = (Customer) database.getIDobj(currentLogin);
        return allCustomerOrders(customer);
    }

    @Override
    public void setQuantity(Order order, Product product, int quantity) {

        database.addRelation(order, product);
        database.setRelationContext(order, product, Integer.toString(quantity));
    }

    @Override
    public int getQuantity(Order order, Product product) {
        String output =  database.getRelationContext(order,  product);
        if(output == null ){
            return 0;
        }else{
            return Integer.valueOf(output);
        }
    }

    @Override
    public void addProductToOrder(Order order, String product_id, int quantity) {
        Product product = new Product(product_id);
        database.addRelation(order, product);
        database.setRelationContext(order, product, Integer.toString(quantity));
    }

    @Override
    public void setCurrentLogin(IDobj newLogin) {
        currentLogin = newLogin;
    }

}
