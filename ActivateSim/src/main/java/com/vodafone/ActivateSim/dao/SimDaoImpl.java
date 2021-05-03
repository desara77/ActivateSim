package com.vodafone.ActivateSim.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.vodafone.ActivateSim.model.Sim;



@Repository
public class SimDaoImpl implements SimDao {

	/*
	 * @Autowired Sim sim;
	 */
	@Autowired
	MongoTemplate mongotem;
	
	@Override
	public List<Sim> getSimObj(String sim_id) {
		// TODO Auto-generated method stub
		
		Query q1 = new Query();
		q1.addCriteria(Criteria.where("Sim_Id").is(sim_id));
		//where Sim_Id=':value'
		List<Sim> s =   new ArrayList<Sim>(); 
		s=	mongotem.find(q1, Sim.class);
		return s ;
	}

}
