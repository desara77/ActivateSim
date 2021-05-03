package com.ibm.healthplanner.model;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 
 * @author DivyaKV
 *  for input from user
 *  
 */

@Document(collection = "patient")
public class Patient_in extends BaseEntity{
    

	private PatientName name;
	private String gender;
	private int age;
	private double height;
	private double weight;
	private String city;
	
	public PatientName getName() {
		return this.name;
	}

	public void setName(PatientName name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Patient_in(PatientName name, String gender, int age, double height, double weight, String city) {
		super();
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.height = height;
		this.weight = weight;
		this.city = city;
	}

	public Patient_in() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Patient_in [name=" + name + ", gender=" + gender + ", age=" + age + ", height=" + height + ", weight="
				+ weight + ", city=" + city + ", getId()=" + getId() + ", toString()=" + super.toString()
				+  "]";
	}

	

	

	
}
