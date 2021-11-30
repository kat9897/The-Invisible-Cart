package com.example.b07_final_project.helper;

public class Product_Card {
    private String name;
    private String price;
    private String quantity;
    private String brand;

    public Product_Card(String name, String price, String brand){
        this.name = name;
        this.price = price;
        this.brand = brand;
    }

    public Product_Card(String name, String price, String quantity, String brand){
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
