package com.java.workout.designpattern;

/**
 * 
 * @author Jeyanthkumar_S
 * Creational design pattern => Prototype design pattern
 * in Java object creation process is time consuming and costly, so instead of creating object every time we can create copy of existing object. So using this design pattern we can create object in easiest way. In prototype pattern object cloning is used to create object copies, which is implemented using clone() method.
 */

//Prototype interface
interface Shape {
 Shape clone();  // Make a copy of itself
 void draw();    // Draw the shape
}

//Concrete prototype
class Circle implements Shape {
 private String color;

 // When you create a circle, you give it a color.
 public Circle(String color) {
     this.color = color;
 }

 // This creates a copy of the circle.
 @Override
 public Shape clone() {
     return new Circle(this.color);
 }

 // This is how a circle draws itself.
 @Override
 public void draw() {
     System.out.println("Drawing a " + color + " circle.");
 }
}

//Client code
class ShapeClient {
 private Shape shapePrototype;

 // When you create a client, you give it a prototype (a shape).
 public ShapeClient(Shape shapePrototype) {
     this.shapePrototype = shapePrototype;
 }

 // This method creates a new shape using the prototype.
 public Shape createShape() {
     return shapePrototype.clone();
 }
}

//Main class
public class PrototypeDesignPattern {
 public static void main(String[] args) {
     // Create a concrete prototype (a red circle).
     Shape circlePrototype = new Circle("red");

//     // Create a client and give it the prototype.
//     ShapeClient client = new ShapeClient(circlePrototype);
//
//     // Use the prototype to create a new shape (a red circle).
//     Shape redCircle = client.createShape();
     
     Shape redCircle = circlePrototype.clone();

     // Draw the newly created red circle.
     circlePrototype.draw();
     redCircle.draw();
 }
}
