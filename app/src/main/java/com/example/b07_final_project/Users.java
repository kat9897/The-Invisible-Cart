package com.example.b07_final_project;

public class Users {
    private String name,PhoneNumber, Email, Password, Uid;
    // 0 for customer
    // 1 for Owner
    private int userType;

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getName() {
        return name;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public Users(int userType,String uid,String name, String phoneNumber, String email, String password) {
        this.name = name;
        PhoneNumber = phoneNumber;
        Email = email;
        Password = password;
        this.Uid = uid;
        this.userType = userType;

    }
}
