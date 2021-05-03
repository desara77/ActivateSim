package com.ibm.healthplanner.model;

/**
 * 
 * @author DivyaKV
 *Request class for nutritionix  exercise-calorie api
 */
public class CalorieBurnRequest {
	
	private String query;
	private String gender;
	private double weight_kg;
	private double height_cm;
	private int age;
		
	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public double getWeight_kg() {
		return weight_kg;
	}

	public void setWeight_kg(double weight_kg) {
		this.weight_kg = weight_kg;
	}

	public double getHeight_cm() {
		return height_cm;
	}

	public void setHeight_cm(double height_cm) {
		this.height_cm = height_cm;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public CalorieBurnRequest(String query, String gender, double weight_kg, double height_cm, int age) {
		super();
		this.query = query;
		this.gender = gender;
		this.weight_kg = weight_kg;
		this.height_cm = height_cm;
		this.age = age;
	}

	public CalorieBurnRequest() {
		// TODO Auto-generated constructor stub
	}
	
	

}
