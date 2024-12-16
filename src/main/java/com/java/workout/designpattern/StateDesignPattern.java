package com.java.workout.designpattern;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * @author Jeyanthkumar_S
 * Behavioral design pattern => State design pattern
 * In the state pattern, a state machine is implemented by implementing each individual state as a derived class of the state pattern interface, each class which implements the state interface defines a method in its own way to change the state of the object.
 * An object should change its behavior when its internal state changes.
 * Each state should be defined independently.
 * Adding new states should not affect the other states or functioning.
 */

interface ProdcutState {
    void handleRequest();
}

class ReadyState implements ProdcutState {
    @Override
    public void handleRequest() {
        System.out.println("Ready state: Stock full Please select a product.");
    }
}

class ProductMediumState implements ProdcutState {
    @Override
    public void handleRequest() {
        System.out.println("Product medium level of count. Please select a product");
    }
}

class ProductLowState implements ProdcutState {
    @Override
    public void handleRequest() {
        System.out.println("Product low level of count. Please select a product");
    }
}

class ProductOutOfStockState implements ProdcutState {
    @Override
    public void handleRequest() {
        System.out.println("Out of stock state: Product unavailable. Please select another product.");
    }
}

class ProductContext {
    private ProdcutState state;

    public void setState(ProdcutState state) {
        this.state = state;
    }

    public void request() {
        state.handleRequest();
    }
}

public class StateDesignPattern {
    public static void main(String[] args) {
        // Create context
    	ProductContext productContext = new ProductContext();
    	
    	List<Product> productList = new ArrayList<Product>();
    	productList.add(new Product(1, "Horlicks", 15));
    	productList.add(new Product(1, "Complan", 8));
    	productList.add(new Product(1, "Boost", 4));
    	productList.add(new Product(1, "Manna", 0));
    	
    	for (Iterator iterator = productList.iterator(); iterator.hasNext();) {
			Product product = (Product) iterator.next();
			if(product.getCount()>10) {
				System.out.println("Product: "+product.getProductName() +" Current status...");
				productContext.setState(new ReadyState());
				productContext.request();
			}else if(product.getCount()>5 && product.getCount()<10) {
				System.out.println("Product: "+product.getProductName() +" Current status...");
				productContext.setState(new ProductMediumState());
				productContext.request();
			}else if(product.getCount()>0 && product.getCount()<5) {
				System.out.println("Product: "+product.getProductName() +" Current status...");
				productContext.setState(new ProductLowState());
				productContext.request();
			}else if(product.getCount()<=0) {
				System.out.println("Product: "+product.getProductName() +" Current status...");
				productContext.setState(new ProductOutOfStockState());
				productContext.request();
			}
			
		}
    }
}


class Product{
	int id;
	String productName;
	int count;
	
	public Product(int id, String productName, int count) {
		super();
		this.id = id;
		this.productName = productName;
		this.count = count;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}