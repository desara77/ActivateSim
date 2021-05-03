package com.vodafone.ActivateSim.dao;

import com.vodafone.ActivateSim.model.ActivateSimResp;
import com.vodafone.ActivateSim.model.Sim;

public interface ActivateSimDao {
	
	
	public ActivateSimResp updateSim(Sim sim, String simId);

}
