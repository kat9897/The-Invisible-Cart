package com.example.b07_final_project.helper;

public class Store extends IDobj {

	private String name; //unique

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int getType() {
		return IDobj.STORE;
	}

	public Store(String ID) {
		super(ID);
	}

}
