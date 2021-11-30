package com.example.b07_final_project.helper;

public class Customer extends IDobj {

	private String email; // unique
	private String name;
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int getType() {
		return IDobj.CUSTOMER;
	}

	public Customer(String ID) {
		super(ID);
	}

}
