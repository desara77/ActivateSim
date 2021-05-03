package com.vodafone.ActivateSim.service;

import com.vodafone.ActivateSim.model.ActivateSimResp;
import com.vodafone.ActivateSim.model.Sim;

public interface ActivateSimService {
	
	
	public ActivateSimResp updateSim(Sim sim, String simId);

}
