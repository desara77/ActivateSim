package com.ibm.healthplanner.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.healthplanner.service.CalorieService;

/**
 * 
 * @author DivyaKV
 *
 */

@RestController
public class CalorieController {
	
	private static final Logger log = LoggerFactory.getLogger(CalorieController.class);
	
	@Autowired
	CalorieService calorieService;
	
	
	@PostMapping(value = "patient/calorie")
    public String recordCalorie( @RequestParam String id,@RequestParam String exercise) {  
		log.info("Inside recordCalorie  --  ");
		return calorieService.recordCalorieBurned(id, exercise);
    }
	
	@DeleteMapping(value = "patient/calorie/{id}")
	public ResponseEntity<String> deleteUserCollection(@PathVariable("id") String patientId) {
		calorieService.deleteUserCollection(patientId);
		return new ResponseEntity<>("User Calorie record is deleted!",HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping(value = "patient/calorie-record")
	public void deleteCalorieRecordbyDate(@RequestParam String date,@RequestParam String collectionName) {
		calorieService.deleteRecordbyDate(date, collectionName);
	}
	
	@GetMapping(value = "/")
	public String checkHealthApi() {
		return "OK";
	}
	

}
