package com.java.workout.classes;

public class Singleton {

	private static Employee emp; //Lazy loading
	//	private static Employee emp = new Employee(1, "JK", "jk@gmail.com", 7094803365); //Eager loading
	private Singleton() { //private constructor to avoid object creation for singleton class
	}

	public static Employee getEmpInstance() {
		if(emp==null) {
			emp = new Employee(1, "JK", "jk@gmail.com", new Long("7094803365"));
		}
		return emp;
	}

}
