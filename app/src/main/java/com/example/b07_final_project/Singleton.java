package com.example.b07_final_project;

public class Singleton{

    static Presenter ID;

    static Presenter getID() {
        if (ID == null)
            ID = new Singleton();
        return ID;
    }

    private final FirebaseModel database;

    private Singleton() {
        database = new FirebaseModel();
    }
    
}
