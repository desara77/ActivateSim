package com.vodafone.ActivateSim.dao;

import java.util.List;

import com.vodafone.ActivateSim.model.Sim;



public interface SimDao {
	
	
	public List<Sim> getSimObj(String sim_id);
	

}
