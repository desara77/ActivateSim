package com.ibm.healthplanner.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.ibm.healthplanner.model.Patient;
import com.ibm.healthplanner.model.PatientName;

@Repository
public class PatientDAOImpl implements PatientDAO {
	
    @Autowired
    private MongoTemplate mongoTemplate;
    
    public static final String COLLECTION_NAME = "patient";


	@Override
	public Patient savePatient(Patient patient) {
       return mongoTemplate.save(patient, COLLECTION_NAME);
	}
	
	@Override
	public List<Patient> getPatientbyName(PatientName name) {
		Query q = new Query();
		q.addCriteria(Criteria.where("name.firstName").is(name.getFirstName())
				.and("name.lastName").is(name.getLastName()));
		
		List<Patient> patientList = mongoTemplate.find(q, Patient.class);
		return patientList;
	}
	
	@Override
	public List<Patient> getPatientbyFirstName(String name) {
		Query q = new Query();
		q.addCriteria(Criteria.where("name.firstName").is(name));
		
		List<Patient> patientList = mongoTemplate.find(q, Patient.class);
		return patientList;
	}
	
	
	
	@Override
	public Patient getPatientbyid(String id) {
		
		Patient patientSingle = mongoTemplate.findById(id, Patient.class,COLLECTION_NAME);
		return patientSingle;
	}

	@Override
	public List<Patient> listPatient() {
        return mongoTemplate.findAll(Patient.class, COLLECTION_NAME);
	}

	@Override
	public void deletePatient(String id) {
		mongoTemplate.remove(new Query(Criteria.where("_id").is(id)), COLLECTION_NAME);

	}


}
