package com.thyateira.dependency.injector.context;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.thyateira.dependency.injector.context.loader.FileOperations;

public class ContextManager {

	private static String baseFilePath = "app/context.properties";

	private static ContextManager manager = null;
	
	private static Map<String, Object> instances = null;
	
	private ContextManager() {
		init();
	}
	
	public static ContextManager getInstance() {
		
		if(manager == null) {
			manager = new ContextManager();
		}
		return manager;
	}
	
	public <T> T get(String key, Class<T> clazz, boolean singleInstance) {
		
		if(singleInstance) {
			return clazz.cast(instances.get(key));
		}
		
		if(instances.containsKey(key)) {
			return clazz.cast(createInstance(clazz));
		}
		
		return null;
	}
	
	private void init() {
		
		if(instances == null) {
			instances = new HashMap<String, Object>();
		}
		
		try {
			
			File contextFile = FileOperations.getFile(baseFilePath);
			
			List<String> lines = FileOperations.readAsList( contextFile );
			
			System.out.println("====> DEPENDENCY CONTEXT INIT IS STARTED");
			
			for(String line : lines) {
				
				// "salary=com.thyateira.dependency.injector.model.Salary"
				
				String[] keyValue = line.split("=");
				String key = keyValue[0];
				String classFullPackagePath = keyValue[1];
				
				Class<?> clazz = Class.forName(classFullPackagePath);
				
				printClassDetails(clazz);
				
				Object instance = createInstance(clazz);
				if(instance != null) {
					instances.put(key, instance);
				}
			}
			System.out.println("====> DEPENDENCY CONTEXT INIT IS COMPLETED");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private void printClassDetails(Class<?> clazz) {
		
		System.out.println("*** Context Dependency Class Details ***");
		
		String name = clazz.getName();
		System.out.println("Name: " + name);
		
		String simpleName = clazz.getSimpleName();
		System.out.println("Simple Name: " + simpleName);
		
		Package packageOfClass = clazz.getPackage();
		System.out.println("Package Name: " + packageOfClass.getName());
		
		Class<?> superClass = clazz.getSuperclass();
		System.out.println("Super Class Name: " + superClass.getSimpleName());
		
		Class<?>[] interfaces = clazz.getInterfaces();
		if(interfaces != null) {
			for(Class<?> interfaceClass : interfaces) {
				System.out.println("Interface Name: " + interfaceClass.getSimpleName());
			}
		}
		
		Constructor<?>[] constructors = clazz.getConstructors();
		if(constructors != null) {
			for(Constructor<?> constructor : constructors) {
				System.out.println(
						"Constructor Name: " + constructor.getName() + 
						", param count: " + constructor.getParameterCount());
			}
		}
		
		Method[] methods = clazz.getMethods();
		if(methods != null) {
			for(Method method : methods) {
				System.out.println(
						"Method Name: " + method.getName() + 
						", Param count: " + method.getParameterCount() + 
						", Return Type: " + method.getReturnType().getTypeName());
			}
		}
		
		
		Field[] fields = clazz.getDeclaredFields();
		if(fields != null) {
			for(Field field : fields) {
				System.out.println("Name: " + field.getName() + ", Type: " + field.getType().getName());
			}
		}
		
		System.out.println("*****************************************");
	}
	
	private Object createInstance(Class<?> clazz) {
		
		try {
			return clazz.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
}
