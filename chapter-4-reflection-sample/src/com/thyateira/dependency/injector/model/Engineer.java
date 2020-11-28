package com.thyateira.dependency.injector.model;

import java.io.Serializable;

public class Engineer implements Serializable {

	private static final long serialVersionUID = -368337642999502071L;
	
	private String name;
	private String lastName;
	private int age;
	private String department;
	private Salary salary;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public Salary getSalary() {
		return salary;
	}
	public void setSalary(Salary salary) {
		this.salary = salary;
	}
}
