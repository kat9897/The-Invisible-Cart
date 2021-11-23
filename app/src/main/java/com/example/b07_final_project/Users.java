package com.example.b07_final_project;

public class Users {
    private String name,PhoneNumber, Email, Password;

    public String getName() {
        return name;
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

    public Users(String name, String phoneNumber, String email, String password) {
        this.name = name;
        PhoneNumber = phoneNumber;
        Email = email;
        Password = password;
    }
}
