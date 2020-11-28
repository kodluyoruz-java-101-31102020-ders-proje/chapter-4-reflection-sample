package com.thyateira.dependency.injector.printer;

import com.thyateira.dependency.injector.model.Salary;

public class SalaryPrinter {

	public static void print(Salary salary) {
		
		System.out.println("PDF Salary: " + salary.getValue() + " " + salary.getCurrency());
		
	}
	
}
