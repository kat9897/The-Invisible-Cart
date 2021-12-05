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
}