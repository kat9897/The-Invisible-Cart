package com.example.b07_final_project.helper;

public class Order_ extends IDobj {

	public static final int INCOMPLETE = 0;
	public static final int COMPLETE = 1;

    private int status = Order_.INCOMPLETE;

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
