package com.ibm.healthplanner.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.healthplanner.dao.CounterDAO;
import com.ibm.healthplanner.dao.PatientDAO;
import com.ibm.healthplanner.model.Patient;
import com.ibm.healthplanner.model.PatientName;
import com.ibm.healthplanner.model.Patient_in;

/**
 * 
 * @author DivyaKV
 *all patient related operations
 *
 */

@Service
public class PatientServiceImpl implements PatientService {

	@Autowired
    private PatientDAO patientDao;
	
	@Autowired
	private CounterDAO counterDao;
	
	public Patient createPatient(Patient_in patientin) 
	{
		List<Patient> patientList = patientDao.getPatientbyName(patientin.getName());
		if(patientList==null || patientList.isEmpty()) {
			Patient patient =new Patient();
			patient.setName(patientin.getName());
			patient.setGender(patientin.getGender());
			patient.setAge(patientin.getAge());
			patient.setHeight(patientin.getHeight());
			patient.setWeight(patientin.getWeight());
			patient.setCity(patientin.getCity());
			
		    //String id = "P" + "-" + System.currentTimeMillis();
			int id = getNextSeqId();
		    patient.setId(Integer.toString(id));
		    
		    double bmiValue = getBmi(patient.getWeight(), patient.getHeight());
		    patient.setBmi(bmiValue);
		    
		    double idealWeight = getIdealWeight(patient.getHeight());
			patient.setIdealWeight(idealWeight);
			
			double bmrValue = getbmrValue(patient);
			patient.setBmr(bmrValue);
			
			String collectionName = "user"+patient.getId();
			patient.setCalorieCollection(collectionName);
			
			Patient record = patientDao.savePatient(patient);
			return record;
		}
		return null;
		
	}
	
	public Patient getPatientbyid(String id) {
		return patientDao.getPatientbyid(id);
	}
	
	public List<Patient> getPatientbyName(PatientName name) {
		return patientDao.getPatientbyName(name);
	}
	
	public List<Patient> getPatientbyFirstName(String name) {
		return patientDao.getPatientbyFirstName(name);
	}
	
	public List<Patient> listPatient() {
		return patientDao.listPatient();
	}
	

	public void deletePatient(String id) {
		patientDao.deletePatient(id);
	}
	
	
	
	
	private double getbmrValue(Patient user) {
		double bmrVal = 0;
		if(user.getGender().equalsIgnoreCase("female")){
			 bmrVal = 10*user.getWeight() + 6.25*user.getHeight() - 5*user.getAge() - 161;
		}else if (user.getGender().equalsIgnoreCase("male")) {
			 bmrVal = 10*user.getWeight() + 6.25*user.getHeight() - 5*user.getAge() + 5;
		}  
		return bmrVal;
	}

	private double getIdealWeight(double height) {
		double heightm = height/100;
		double lowRange = 18.5 * (heightm*heightm);
		double upRange = 25 * (heightm*heightm);
		double idealweight = (lowRange +upRange)/2;
		BigDecimal weight = new BigDecimal(idealweight);
		weight = weight.setScale(3, RoundingMode.HALF_UP);
		idealweight = Double.parseDouble(weight.toString());
		return idealweight;
	}

	private double getBmi(double weight, double height) {
		double heightm = height/100;
		double bmi = weight/(heightm * heightm);
		BigDecimal bmiBig = new BigDecimal(bmi);
		bmiBig = bmiBig.setScale(3, RoundingMode.HALF_UP);
		bmi = Double.parseDouble(bmiBig.toString());
		return bmi;
	}
	
	public int getNextSeqId() {
		return counterDao.getNextId();
	}

	
}
