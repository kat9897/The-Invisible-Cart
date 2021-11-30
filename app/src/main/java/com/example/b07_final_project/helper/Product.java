package com.example.b07_final_project.helper;

public class Product {
    private int id;
    private String name;
    private double price;
    private String brand;

    public Product() {
    }
    public Product(int id, String name, double price, String brand) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.brand = brand;

    }
    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getBrand() { return brand; }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }
    public void setBrand(String brand) { this.brand = brand; }

    @Override
    public String toString() {
        return "Product id: " + id + ", name: " + name + ", price: " + price +
                ", and brand: " + brand;
    }

}
