package com.thyateira.dependency.injector.model;

import java.io.Serializable;

public class Salary implements Serializable, Cloneable {

	private static final long serialVersionUID = 3190071008620055145L;
	
	private double value;
	private String currency;
	
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
}
