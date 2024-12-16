package com.java.workout.designpattern;

/**
 * 
 * @author Jeyanthkumar_S
 * Structural Design Pattern: Facade Design Pattern
 * Facade pattern does beautiful work by hiding all the complexities of your system and provides a simpler interface API to use. You can use this patterns if an entry point is needed to each level of layered software, or
the abstractions and implementations of a subsystem are tightly coupled.
 */

interface Animal {
	public void speak();
}

class Dog implements Animal {
	 
	@Override
	public void speak() {
		System.out.println("Dog Speaks :: Bow!! Bow!!");
	}
 
}

class Cat implements Animal {
	 
	@Override
	public void speak() {
		System.out.println("Cat Speaks :: Meow!! Meow!!");
	}
 
}

class AnimalFacade {
	
	private Animal dog;
	private Animal cat;
	
	public AnimalFacade() {
		dog = new Dog();
		cat = new Cat();
	}
	
	public void speakDog() {
		dog.speak();
	}
	
	public void speakCat() {
		cat.speak();
	}

}

public class FacadeDesignPattern {
	 
	public static void main(String[] args) {
		
		AnimalFacade af = new AnimalFacade();
		af.speakDog();
		af.speakCat();
		
	}
}
