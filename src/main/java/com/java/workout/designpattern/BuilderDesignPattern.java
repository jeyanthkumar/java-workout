package com.java.workout.designpattern;

/**
 * 
 * @author Jeyanthkumar_S
 * Creational design pattern => Builder design pattern
 *
 */
public class BuilderDesignPattern {
	
	private final int id;
	private final String name;
	private final String addr;
	private final String phone;
	
	public BuilderDesignPattern(int id,String name){
//		this.id = id;
//		this.name = name;
//		this.addr = null;
//		this.phone = null;
		this(id, name, null, null);
	}
	
	public BuilderDesignPattern(int id, String name, String addr){
		this(id, name, addr, null);
	}
	
	public BuilderDesignPattern( String name, int id, String phone){
		this(id, name, null, phone);
	}
	
	
	public BuilderDesignPattern(int id,String name,String addr, String phone){
		this.id = id;
		this.name = name;
		this.addr = addr;
		this.phone = phone;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getAddr() {
		return addr;
	}
	
	public String getPhone() {
		return phone;
	}

	@Override
	public String toString() {
		return "BuilderDesignPattern [id=" + id + ", name=" + name + ", addr=" + addr + "]";
	}
	
	

}
