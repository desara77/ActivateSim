package com.ibm.healthplanner.service;

import java.util.HashMap;

import org.springframework.http.ResponseEntity;

import com.ibm.healthplanner.model.CalorieBurnRequest;
import com.ibm.healthplanner.model.Patient;

public interface CalorieService {
	
	public CalorieBurnRequest setApiRequest(Patient patient, String exercise);
	
	public String getJsonRequest(CalorieBurnRequest request);
	
	public ResponseEntity<String> getCalorie(String apiRequest);
	
	public HashMap<String, String> getcaloriefromResponse( ResponseEntity<String> response);

	public String saveRecord(HashMap<String, String> activity_calorie, Patient patient);

	public String recordCalorieBurned(String id, String exercise);

	public void deleteUserCollection(String patientId);
	
	public void deleteCalorieCollection(String collectionName);

	public void deleteRecordbyDate(String date,String collectionName);
}
