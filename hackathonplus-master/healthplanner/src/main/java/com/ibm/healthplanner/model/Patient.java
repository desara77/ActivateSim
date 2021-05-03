package com.ibm.healthplanner.model;

import org.springframework.data.mongodb.core.mapping.Document;
/**
 * 
 * @author DivyaKV
 * represents the table of patient details
 * 
 */

@Document(collection = "patient")
public class Patient extends Patient_in{
    
	private double bmi;
	private double idealWeight;
	private double bmr;
	private String calorieCollection;
	
	public double getBmi() {
		return bmi;
	}

	public void setBmi(double bmi) {
		this.bmi = bmi;
	}

	public double getIdealWeight() {
		return idealWeight;
	}

	public void setIdealWeight(double idealWeight) {
		this.idealWeight = idealWeight;
	}

	public double getBmr() {
		return bmr;
	}

	public void setBmr(double bmr) {
		this.bmr = bmr;
	}
	
		
	public String getCalorieCollection() {
		return calorieCollection;
	}

	public void setCalorieCollection(String calorieCollection) {
		this.calorieCollection = calorieCollection;
	}

	public Patient(PatientName name, String gender, int age, double height, double weight, String city, double bmi,
			double idealWeight, double bmr, String calorieCollection) {
		super(name, gender, age, height, weight, city);
		this.bmi = bmi;
		this.idealWeight = idealWeight;
		this.bmr = bmr;
		this.calorieCollection = calorieCollection;
	}

	public Patient() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Patient [bmi=" + bmi + ", idealWeight=" + idealWeight + ", bmr=" + bmr + ", calorieCollection="
				+ calorieCollection + ", getName()=" + getName() + ", getAge()=" + getAge() + ", getGender()="
				+ getGender() + ", getHeight()=" + getHeight() + ", getWeight()=" + getWeight() + ", getCity()="
				+ getCity() + ", toString()=" + super.toString() + ", getId()=" + getId() +  "]";
	}

	

	
}
