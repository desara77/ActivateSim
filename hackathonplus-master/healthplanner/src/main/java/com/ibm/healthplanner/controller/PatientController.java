package com.ibm.healthplanner.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.healthplanner.model.Patient;
import com.ibm.healthplanner.model.Patient_in;
import com.ibm.healthplanner.service.CalorieService;
import com.ibm.healthplanner.service.PatientService;

/**
 * 
 * @author DivyaKV
 *
 */

@RestController
public class PatientController {

	@Autowired
	PatientService patientService;
	
	@Autowired
	CalorieService calorieService;
	
    @GetMapping(value = "/patient/all")
    public List<Patient> getPatientList() {  
    	return  patientService.listPatient();  
    }  
    
    @GetMapping(value = "/patient/name" )
    public List<Patient> getPatientByFirstName(@RequestParam String name) {  
    	return  patientService.getPatientbyFirstName(name);  
    }  
    @PostMapping(value = "/patient/register")  
    public ResponseEntity<?> createPatient(@RequestBody Patient_in patient) {
    	Patient patientRecord = patientService.createPatient(patient);
        return new ResponseEntity<>(patientRecord, HttpStatus.CREATED) ;
    }
    
    @DeleteMapping(value = "/patient/{id}") 
    public void deletePatient(@PathVariable("id") String id) {  
    	patientService.deletePatient(id); 
    	
    }
        
}
