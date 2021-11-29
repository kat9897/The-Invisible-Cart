package com.example.b07_final_project;

public class Order extends IDobj {

    private int status;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public int getType() {
		return IDobj.ORDER;
	}

	public Order(String ID) {
		super(ID);
	}

}
