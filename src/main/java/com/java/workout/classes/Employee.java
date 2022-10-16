package com.java.workout.classes;

import java.util.Objects;

public class Employee {

	int id;
	String name;
	String email;
	Long phone;
	
	
	public Employee(int id, String name, String email, Long phone) {
		super();
		System.out.println("Employee created with ID: "+id+", NAME: "+name);
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
	}
	
	public Employee() {
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getPhone() {
		return phone;
	}
	public void setPhone(Long phone) {
		this.phone = phone;
	}
	
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", email=" + email + ", phone=" + phone + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(email, id, name, phone);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(email, other.email) && id == other.id && Objects.equals(name, other.name)
				&& phone == other.phone;
	}
	
}
