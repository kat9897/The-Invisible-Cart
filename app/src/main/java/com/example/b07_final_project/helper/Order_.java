package com.example.b07_final_project.helper;

public class Order_ extends IDobj {

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

	public Order_(String ID) {
		super(ID);
	}

}
