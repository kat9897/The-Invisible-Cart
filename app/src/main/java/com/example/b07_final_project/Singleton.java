package com.example.b07_final_project;

public class Singleton implements Presenter {

    static Presenter ID;

    static Presenter getID() {
        if (ID == null)
            ID = new Singleton();
        return ID;
    }

    private final Model database;

    private Singleton() {
        database = new FirebaseDatabase();
    }
    
}
