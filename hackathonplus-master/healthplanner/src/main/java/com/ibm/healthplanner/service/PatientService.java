package com.ibm.healthplanner.service;

import java.util.List;

import com.ibm.healthplanner.model.Patient;
import com.ibm.healthplanner.model.PatientName;
import com.ibm.healthplanner.model.Patient_in;

public interface PatientService {
	
	public Patient createPatient(Patient_in patientin); 
	
	public Patient getPatientbyid(String id);
	
	public List<Patient> getPatientbyName(PatientName name);
	public List<Patient> getPatientbyFirstName(String name);

	public List<Patient> listPatient();

	public void deletePatient(String id);
	
	public int getNextSeqId();
	
}
