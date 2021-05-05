package com.vodafone.ActivateSim.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vodafone.ActivateSim.dao.SimDao;
import com.vodafone.ActivateSim.model.MessageConstants;
import com.vodafone.ActivateSim.model.Sim;
import com.vodafone.ActivateSim.model.StatusResponseWithMessage;



@Service
public class SimServiceImpl implements SimService {
	
	String methodname = "activateSim";

	@Autowired
	SimDao simDao;
	@Autowired
	MessageConstants msgC;
	
	StatusResponseWithMessage statusWithMsg = new StatusResponseWithMessage();
	
	@Override
	public boolean simPrevalidation(String sim_id) throws Exception {
		String errorMsg =null;
		// TODO Auto-generated method stub
		
	    final String regex4Digits = "\\p{Digit}";
		List<Sim> simObj = new ArrayList<Sim>();
		Sim  simobject = new Sim();
		if (sim_id !=null && !sim_id.isEmpty()) {
			//Sim checks here
			
			simObj=simDao.getSimObj(sim_id);
			
			
		}
		
		if (simObj!=null && !simObj.isEmpty()) {
			if (simObj.get(0).getStatus()=="o") {
				
				
				
			}
			else if (simObj.get(0).getStatus()=="a") {
				statusWithMsg.addErrorMesge(msgC.SIM_ACTIVATED_ALREADY, methodname);
				//errorMsg = msgC.SIM_ACTIVATED_ALREADY;
				//throw new ServiceException(errorMsg);
				return false;
			}
			else {
				//errorMsg = msgC.SIM_CANNOT_BE_ACTIVATED;
				//throw new ServiceException(errorMsg);
				//Sim cannot be activated error thrown
				statusWithMsg.addErrorMesge(msgC.SIM_CANNOT_BE_ACTIVATED, methodname);
				return false;
			}
		}
		
	

		
		String phoneNum = null;
		phoneNum=simobject.getMsisdnNum().toString();
		if (simobject.getMsisdnNum()!=null) {
			
		    if (phoneNum.length()==10) {
		       //Valid phone num
		    }
		    
		    else{
		    	//errorMsg = msgC.NUMBER_NOT_VALID_WITH_LENGTH;
				//throw new ServiceException(errorMsg);
		    	//Note a Valid phone number with the length  error thrown
		    	statusWithMsg.addErrorMesge(msgC.NUMBER_NOT_VALID_WITH_LENGTH, methodname);
				return false;
		    	
		    }
		}
	
		
		Pattern  pattern = Pattern.compile(regex4Digits);
		Matcher matcher = pattern.matcher(phoneNum); 
	    
		if (!matcher.matches()) {
			/*
			 * errorMsg = msgC.NUMBER_CONTAINS_ILLEGAL_CHARS; throw new
			 * ServiceException(errorMsg);
			 */
			//The phone is containing the illegal charcters.. error thrown
			statusWithMsg.addErrorMesge(msgC.NUMBER_CONTAINS_ILLEGAL_CHARS, methodname);
			return false;
			
		}
	
		return true;
	}
	
	

}
