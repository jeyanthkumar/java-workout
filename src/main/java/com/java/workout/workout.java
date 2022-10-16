package com.java.workout;
import java.io.*;

class workout { 
	static int a= 0;
	static {
		System.out.println("Static block invoked !!!"+a);
	}
	// Initializer block starts..
	{
		// This code is executed before every constructor.
		System.out.println(
				"Common part of constructors invoked !!");
	}
	// Initializer block ends

	// Constructor 1
	// Default constructor
	workout()
	{

		// Print statement
		System.out.println("Default Constructor invoked");
	}

	// Constructor 2
	// Parameterized constructor
	workout(int x)
	{

		// Print statement
		System.out.println(
				"Parameterized constructor invoked"+x);
	}

	static int testReturnInt() {
		return 5;
	}

	static int testReturnInt(testInterface a) {
		return 5;
	}
	// Main driver method
	public static String main(Integer arr[])
	{
		System.out.println("Inside integer main array...");
		return "jeyanth";
	}
	public static void main(String arr[])
	{
		// Creating variables of class type
		workout obj1, obj2;

		// Now these variables are
		// made to object and interpreted by compiler
		obj1 = new workout();

		testInterface testInt = ()->0;
		testInt.readFile("J:\\Projects\\Java\\workout\\java-workout\\src\\main\\resources\\application.properties");
		testInt = workout::testReturnInt;
		obj2 = new workout(testInt.abstract_method());
		GFG ob = new GFG();
		String[] arr1 = {"ad","ak"};
		ob.main(arr1);
//		throw new customArithmaticException();
		throw new ArithmeticException();
	}
}

class customArithmaticException extends ArithmeticException{
	customArithmaticException(){
		
		super("hiiiii");
	}
	
}

@FunctionalInterface
interface testInterface{
	default String readFile(String path) {
		try (FileInputStream fis = new FileInputStream("J:\\Projects\\Java\\workout\\java-workout\\src\\main\\resources\\application.properties")) { 
			int data = fis.read();
			while (data != -1) { 
//				System.out.print(Integer.toHexString(data)); 
				System.out.print((char)data); 
				data = fis.read();
			} 
		} catch (IOException e) {
			System.out.println("Failed to read binary data from File"); e.printStackTrace();
		} // Example 2 - Reading File data using FileReader in Java 
		try (FileReader reader = new FileReader("J:\\Projects\\Java\\workout\\java-workout\\src\\main\\resources\\application.properties")) { 
			int character = reader.read(); 
			while (character != -1) { 
				System.out.print((char) character); character = reader.read(); 
			} 
		} catch (IOException io) { 
			System.out.println("Failed to read character data from File"); io.printStackTrace(); 
		}

		return "data";
	}
	int abstract_method();
}
//Java Program to Illustrate Initializer Block

//Importing required classes

//Main class
class GFG {

	// Initializer block starts..
	{
		// This code is executed before every constructor.
		System.out.println(
				"Common part of constructors invoked !!");
	}
	// Initializer block ends

	// Constructor 1
	// Default constructor
	public GFG()
	{

		// Print statement
		System.out.println("Default Constructor invoked");
	}

	// Constructor 2
	// Parameterized constructor
	public GFG(int x)
	{

		// Print statement
		System.out.println(
				"Parameterized constructor invoked");
	}

	// Main driver method
	public static void main(String arr[])
	{
		System.out.println(arr[0]);
		// Creating variables of class type
		GFG obj1, obj2;

		// Now these variables are
		// made to object and interpreted by compiler
		obj1 = new GFG();

		obj2 = new GFG(0);
	}
}
