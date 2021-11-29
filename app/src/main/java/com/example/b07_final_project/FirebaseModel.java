package com.example.b07_final_project;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FirebaseModel implements Model{

    // fields

    private FirebaseDatabase database;
    private final DatabaseReference refRoot;
    private DataSnapshot dataRoot;

    private final String dirROOT = "Alex_R_Test_Dir";
    private final String dirOWNER = "Owner";
    private final String dirSTORE = "Store";
    private final String dirPRODUCT = "Product";
    private final String dirORDER = "Order";
    private final String dirCUSTOMER = "Customer";
    private final String dirID = "ID";
    private final String dirRELATION = "Relation";


    FirebaseModel() {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        refRoot = database.getReference().child(dirROOT);

        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dataRoot = dataSnapshot;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        refRoot.addValueEventListener(listener);
    }

    // methods

    private DatabaseReference moveToChild(DatabaseReference ref, int type) {
        switch(type) {
            case IDobj.CUSTOMER:
                return ref.child(dirCUSTOMER);
            case IDobj.ORDER:
                return ref.child(dirORDER);
            case IDobj.OWNER:
                return ref.child(dirOWNER);
            case IDobj.PRODUCT:
                return ref.child(dirPRODUCT);
            case IDobj.STORE:
                return ref.child(dirSTORE);
        }
        return null;
    }

    private DataSnapshot moveToChild(DataSnapshot snap, int type) {
        switch(type) {
            case IDobj.CUSTOMER:
                return snap.child(dirCUSTOMER);
            case IDobj.ORDER:
                return snap.child(dirORDER);
            case IDobj.OWNER:
                return snap.child(dirOWNER);
            case IDobj.PRODUCT:
                return snap.child(dirPRODUCT);
            case IDobj.STORE:
                return snap.child(dirSTORE);
        }
        return null;
    }

    @Override
    public IDobj newIDobj(int type) {

        DatabaseReference ref = moveToChild(refRoot, type);
        DatabaseReference refField;

        ref = ref.push();
        String key = ref.getKey();

        refField = ref.child(dirID);
        refField.setValue(key);

        IDobj obj;

        switch (type){
            case IDobj.ORDER:
                obj = new Order_(key);
            break;
            case IDobj.OWNER:
                obj = new Owner(key);
            break;
            case IDobj.PRODUCT:
                obj = new Product_(key);
            break;
            case IDobj.STORE:
                obj = new Store(key);
            break;
            default: //case IDobj.CUSTOMER:
                obj = new Customer(key);
                break;
        }

        return obj;
    }

    @Override
    public void saveIDobj(IDobj obj) {

        DatabaseReference ref = moveToChild(refRoot, obj.getType());
        ref = ref.child(obj.getID());
        DatabaseReference refField;

        switch (obj.getType()) {
            case IDobj.ORDER:
                Order_ order = (Order_) obj;
                refField = ref.child("Status");
                refField.setValue(order.getStatus());
                break;
            case IDobj.OWNER:
                Owner owner = (Owner) obj;
                refField = ref.child("Email");
                refField.setValue(owner.getEmail());
                refField = ref.child("Name");
                refField.setValue(owner.getName());
                refField = ref.child("Password");
                refField.setValue(owner.getPassword());
                refField = ref.child("PhoneNumber");
                refField.setValue(owner.getPhoneNumber());
                break;
            case IDobj.PRODUCT:
                Product_ product = (Product_) obj;
                refField = ref.child("Name");
                refField.setValue(product.getName());
                refField = ref.child("Brand");
                refField.setValue(product.getBrand());
                refField = ref.child("Price");
                refField.setValue(product.getPrice());
                break;
            case IDobj.STORE:
                Store store = (Store) obj;
                refField = ref.child("Name");
                refField.setValue(store.getName());
                break;
            default: //case IDobj.CUSTOMER:
                Customer customer = (Customer) obj;
                refField = ref.child("Email");
                refField.setValue(customer.getEmail());
                refField = ref.child("Name");
                refField.setValue(customer.getName());
                refField = ref.child("Password");
                refField.setValue(customer.getPassword());
                break;
        }
    }

    @Override
    public IDobj getIDobj(int type, String ID) {

        DataSnapshot snap = dataRoot;
        DataSnapshot snapField;
        snap = moveToChild(snap, type);
        snap = snap.child(ID);

        if (!snap.exists())
            return null;

        IDobj obj;

        switch (type) {
            case IDobj.ORDER:
                Order_ order = new Order_(ID);
                obj = order;
                snapField = snap.child("Status");
                order.setStatus((int) snapField.getValue());
                break;
            case IDobj.OWNER:
                Owner owner = new Owner(ID);
                obj = owner;
                snapField = snap.child("Email");
                owner.setEmail((String) snapField.getValue());
                snapField = snap.child("Name");
                owner.setName((String) snapField.getValue());
                snapField = snap.child("Password");
                owner.setPassword((String) snapField.getValue());
                snapField = snap.child("PhoneNumber");
                owner.setPhoneNumber((String) snapField.getValue());
                break;
            case IDobj.PRODUCT:
                Product_ product = new Product_(ID);
                obj = product;
                snapField = snap.child("Name");
                product.setName((String) snapField.getValue());
                snapField = snap.child("Brand");
                product.setBrand((String) snapField.getValue());
                snapField = snap.child("Price");
                product.setPrice((double) snapField.getValue());
                break;
            case IDobj.STORE:
                Store store = new Store(ID);
                obj = store;
                snapField = snap.child("Name");
                store.setName((String) snapField.getValue());
                break;
            default: //case IDobj.CUSTOMER:
                Customer customer = new Customer(ID);
                obj = customer;
                snapField = snap.child("Email");
                customer.setEmail((String) snapField.getValue());
                snapField = snap.child("Name");
                customer.setName((String) snapField.getValue());
                snapField = snap.child("Password");
                customer.setPassword((String) snapField.getValue());
                break;
        }

        return obj;
    }

    @Override
    public IDobj getIDobj(IDobj obj) {
        return getIDobj(obj.getType(), obj.getID());
    }

    private ArrayList<IDobj> getAllIDobjAt(DataSnapshot root,int type) {

        ArrayList<IDobj> output = new ArrayList<>();

        DataSnapshot snap = root;
        snap = moveToChild(snap, type);

        for (DataSnapshot snapChild : snap.getChildren())
            output.add(getIDobj(type, (String) snapChild.child(dirID).getValue()));

        return output;
    }

    @Override
    public ArrayList<IDobj> getAllIDobj(int type) {

        return getAllIDobjAt(dataRoot, type);
    }

    private void addOneWayRelation(IDobj obj1, IDobj obj2) {

        DatabaseReference ref = refRoot;

        ref = moveToChild(ref, obj1.getType());
        ref = ref.child(obj1.getID());
        ref = ref.child(dirRELATION);
        ref = moveToChild(ref, obj2.getType());
        ref = ref.child(obj2.getID());

        ref.setValue(ref.getKey());
    }

    @Override
    public void addRelation(IDobj obj1, IDobj obj2) {
        addOneWayRelation(obj1, obj2);
        addOneWayRelation(obj2, obj1);
    }

    @Override
    public ArrayList<IDobj> getRelations(IDobj obj, int type) {

        DataSnapshot snap = dataRoot;

        snap = moveToChild(snap, obj.getType());
        snap = snap.child(obj.getID());
        snap = snap.child(dirRELATION);

        return getAllIDobjAt(snap, type);
    }

    @Override
    public void deleteIDobj (IDobj obj) {

        DatabaseReference ref = refRoot;

        ref = moveToChild(ref, obj.getType());
        ref = ref.child(obj.getID());

        // delete relations to here

        DataSnapshot snap = dataRoot;
        DataSnapshot snapTyped;
        DatabaseReference refReflect;

        snap = moveToChild(snap, obj.getType());
        snap = snap.child(obj.getID());
        snap = snap.child(dirRELATION);

        for (int type = 1; type <= 5; type++) { // for each type of IDobj

            snapTyped = moveToChild(snap, type);

            for (DataSnapshot snapChild : snapTyped.getChildren()){

                refReflect = moveToChild(refRoot, type);
                refReflect = refReflect.child((String) snapChild.getValue());
                refReflect = refReflect.child(dirRELATION);
                refReflect = moveToChild(refReflect, obj.getType());
                refReflect = refReflect.child(obj.getID());

                refReflect.removeValue();
            }
        }

        // delete this object

        ref.removeValue();
    }

}























