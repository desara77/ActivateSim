package com.vodafone.ActivateSim.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.vodafone.ActivateSim.model.ActivateSimResp;
import com.vodafone.ActivateSim.model.Sim;
import com.vodafone.ActivateSim.model.TicklerRecord;



@Repository
public class ActivateSimDaoImpl implements ActivateSimDao{
	
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	ActivateSimResp resp = new ActivateSimResp();
	
	public List<Sim> findSimById(String id) { 
		  
		Query q1 = new Query();
		q1.addCriteria(Criteria.where("SIM_Id").is(id));
		return mongoTemplate.find(q1, Sim.class); 
	  }
	
	
	public ActivateSimResp updateSim(Sim sim, String simId) {
		
		List<Sim> simRow = findSimById(sim.getSimId()); 
		Sim simIn = new Sim();
		  if(simRow != null && simRow.size() > 0) { 
			  
			  simIn.setStatus("a");
			  
			  mongoTemplate.save(simIn, "Sim");	
			  
              resp.setMessageCode("0");
              resp.setMessageDesc("Sim table for simId- "+simId+" has been updated.");
              
              TicklerRecord tr = new TicklerRecord();
              
              tr.setTicklerCode("0");
              tr.setTicklerStatus("a");
              tr.setClosedDate(new Date());
              tr.setModifiedDate(null);
              tr.setClosedDate(null);
             // tr.setMsisdnNum(new BigDecimal(simRow.get(0).getMsisdnNum()));
              tr.setSimId(new BigDecimal(simId));
              tr.setShortDescription("\"+simId+\" has been updated.\"");
              
              mongoTemplate.insert(tr);
              			  
		  }
		
		else {
			resp.setMessageCode("9999");
            resp.setMessageDesc("Sim table for simId- "+simId+" has not been updated.");
  
		  }
		  
          return resp;
		  
	}

}
