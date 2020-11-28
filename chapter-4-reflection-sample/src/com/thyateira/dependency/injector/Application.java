package com.thyateira.dependency.injector;

import com.thyateira.dependency.injector.context.ContextManager;
import com.thyateira.dependency.injector.model.DetachedClass;
import com.thyateira.dependency.injector.model.Engineer;
import com.thyateira.dependency.injector.model.Salary;

public class Application {

	public static void main(String[] args) {
		
		// Direkt olarak bir nesne üretilemez.
		// ContextManager m = new ContextManager();
		
		ContextManager manager = ContextManager.getInstance();
		
		// direkt olarak nesne üretmiyoruz, reflection ile Context oluşturup buradan yönetiyoruz.
		// Engineer eng = new Engineer();
		
		
		Engineer engineer = manager.get("engineer", Engineer.class, true);
		System.out.println(engineer);
		
		engineer = manager.get("engineer", Engineer.class, true);
		System.out.println(engineer);
		
		engineer = manager.get("engineer", Engineer.class, false);
		System.out.println(engineer);
		
		
		
		Salary salary = manager.get("salary", Salary.class, true);
		System.out.println(salary);
		
		salary = manager.get("salary", Salary.class, true);
		System.out.println(salary);
		
		
		
		DetachedClass detachedClass = manager.get("detachedClass", DetachedClass.class, true);
		System.out.println(detachedClass);
	}

}
