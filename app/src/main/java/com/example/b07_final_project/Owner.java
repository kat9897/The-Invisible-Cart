package com.example.b07_final_project;

public class Owner extends IDobj {

	private String email; // unique
	private String name;
	private String password;
	private String phoneNumber;

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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public int getType() {
		return IDobj.OWNER;
	}

	public Owner(String ID) {
		super(ID);
	}

}
