package com.example.b07_final_project.helper;

//import com.example.b07_final_project.helper.Presenter;

public abstract class IDobj {

    static public final int OWNER = 1;
    static public final int STORE = 2;
    static public final int PRODUCT = 3;
    static public final int ORDER = 4;
    static public final int CUSTOMER = 5;

    abstract public int getType();

    private final String ID;

    public int save() {

        Presenter singleton = Singleton.getID();
        return singleton.save(this);

    }

    public IDobj(String ID) {
        this.ID = ID;
    }

    public String getID() {
        return ID;
    }

}
