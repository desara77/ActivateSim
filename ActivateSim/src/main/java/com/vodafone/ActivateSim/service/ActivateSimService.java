package com.vodafone.ActivateSim.service;

import com.vodafone.ActivateSim.model.Sim;
import com.vodafone.ActivateSim.model.StatusResponseWithMessage;

public interface ActivateSimService {
	
	
	public StatusResponseWithMessage activateSim(Sim sim) throws Exception;

}
