package com.example.b07_final_project;

public class Singleton {

    static Singleton ID;

    static Singleton getID() {
        if (ID == null)
            ID = new Singleton();
        return ID;
    }

    private Singleton() {}
    
}
