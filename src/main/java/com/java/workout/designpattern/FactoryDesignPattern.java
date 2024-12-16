package com.java.workout.designpattern;

/**
 * 
 * @author jeyanthkumar
 * In a complex process, where object creation involves various approaches, this pattern provides a way to centralize the object creation mechanism
 * Code duplication can be avoided
 * Loose coupling is introduced.
 *
 */
interface Country {
	
	public String getArea();
 
	public String getCapital();
	
	default String getClassAsString() {
		return this.getClass().getSimpleName() + " [Area=" + getArea() + ", Capital=" + getCapital() + "]";
	}
}

class India implements Country {
	 
	@Override
	public String getArea() {
		return "3.287 million km²";
	}
 
	@Override
	public String getCapital() {
		return "New Delhi";
	}
 
	@Override
	public String toString() {
		return getClassAsString();
	}
}

class Japan implements Country {
	 
	@Override
	public String getArea() {
		return "377,962 km²";
	}
 
	@Override
	public String getCapital() {
		return "Tokyo";
	}
 
	@Override
	public String toString() {
		return getClassAsString();
	}
}

class Factory {
	
	public static Country getCountry(String c) {
		Country country = null;
		if(c.equalsIgnoreCase("India")) {
			country = new India();
		} else if(c.equalsIgnoreCase("Japan")) {
			country = new Japan();
		}
		return country;
	}
}

public class FactoryDesignPattern {
	 
	public static void main(String[] args) {
		String input = "India";
		Country ci = Factory.getCountry(input);
		System.out.println(ci);
		
		input = "Japan";
		Country cj = Factory.getCountry(input);
		System.out.println(cj);
	}
}