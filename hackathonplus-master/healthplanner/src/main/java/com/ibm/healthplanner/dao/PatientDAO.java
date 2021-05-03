package com.ibm.healthplanner.dao;

import java.util.List;

import com.ibm.healthplanner.model.Patient;
import com.ibm.healthplanner.model.PatientName;

public interface PatientDAO 
{
    
	public Patient savePatient(Patient patient);
    public List<Patient> getPatientbyName(PatientName name);
    public List<Patient> listPatient();
    public void deletePatient(String id);
	Patient getPatientbyid(String id);
	List<Patient> getPatientbyFirstName(String name);
}
