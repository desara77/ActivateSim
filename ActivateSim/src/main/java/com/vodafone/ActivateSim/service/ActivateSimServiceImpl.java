package com.vodafone.ActivateSim.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vodafone.ActivateSim.dao.ActivateSimDao;
import com.vodafone.ActivateSim.model.ActivateSimResp;
import com.vodafone.ActivateSim.model.Sim;
import com.vodafone.ActivateSim.model.StatusResponseWithMessage;



@Service
public class ActivateSimServiceImpl implements ActivateSimService{
	
	private static final Logger log = LoggerFactory.getLogger(ActivateSimServiceImpl.class);
	
	@Autowired
	ActivateSimDao activateSimDao;
	
	@Autowired
	SimService simService;
	
	ActivateSimResp resp = new ActivateSimResp();
	
	StatusResponseWithMessage statusWithMsg = new StatusResponseWithMessage();
	
	  public StatusResponseWithMessage activateSim(Sim sim) throws Exception{
		
		log.info("ActivateSim entered");
		if (prevalidActivateSim(sim, statusWithMsg)) {
				prepareActivateSim(sim);
			}
		
		 return statusWithMsg;
				
		}
	
	
	
	  public boolean prevalidActivateSim(Sim sim, StatusResponseWithMessage statusWithMsg) throws Exception {
		
		
		 boolean ret =  simService.simPrevalidation(sim.getSimId());
		
		  return ret;
	  }
	  
	
	   public void prepareActivateSim(Sim sim) throws Exception{
		
		updateSim(sim, sim.getSimId());
		statusWithMsg.createOkResult();
	  }
	
	
	   public ActivateSimResp updateSim(Sim sim, String simId) {
		
		  resp = activateSimDao.updateSim(sim,simId);
		
		  return resp;
		
	   }

}
