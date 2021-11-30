package com.example.b07_final_project.helper;

public class Product_ extends IDobj {

	private String name;
	private String brand;
	private double price;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public int getType() {
		return IDobj.PRODUCT;
	}

	public Product_(String ID) {
		super(ID);
	}

}
