package com.java.workout.designpattern;

/**
 * 
 * @author Jeyanthkumar_S
 * Creational design pattern => Builder design pattern
 *
 */
public class BuilderDesignPattern2 {

	private final int id; // fields are final private
	private final String name; // fields are final private
	private final String addr; // fields are final private
	private final String phone; // fields are final private

	public static class Builder{
		private final int id; // fields are final private
		private final String name; // fields are final private
		private String addr; // optional fields are non-final private
		private String phone; // optional fields are non-final private
		
		public Builder(int id, String name) { // constructor with mandatory params
			this.id = id;
			this.name = name;
		}

		public Builder setAddr(String addr) {
			this.addr = addr;
			return this; // return builder object
		}
		public Builder setPhone(String phone) {
			this.phone = phone;
			return this; // return builder object
		}

		public BuilderDesignPattern2 build() { // final call main class
			return new BuilderDesignPattern2(this);
		}
	}

	private BuilderDesignPattern2(Builder builder){
		this.id = builder.id;
		this.name = builder.name;
		this.addr = builder.addr;
		this.phone = builder.phone;
	}
	// There is none of setter method, initially value should be passed through builder class
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
