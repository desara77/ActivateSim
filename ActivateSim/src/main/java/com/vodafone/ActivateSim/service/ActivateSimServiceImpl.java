package com.vodafone.ActivateSim.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;

import com.vodafone.ActivateSim.dao.ActivateSimDao;
import com.vodafone.ActivateSim.model.ActivateSimResp;
import com.vodafone.ActivateSim.model.Sim;



@Service
public class ActivateSimServiceImpl implements ActivateSimService{
	
	@Autowired
	ActivateSimDao activateSimDao;
	
	ActivateSimResp resp = new ActivateSimResp();
	
	public ActivateSimResp updateSim(Sim sim, String simId) {
		
		resp = activateSimDao.updateSim(sim,simId);
		
		return resp;
		
	}

}
