package com.vodafone.ActivateSim.controller;

import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vodafone.ActivateSim.model.ActivateSimResp;
import com.vodafone.ActivateSim.model.Sim;
import com.vodafone.ActivateSim.model.StatusResponseWithMessage;
import com.vodafone.ActivateSim.service.ActivateSimService;
import com.vodafone.ActivateSim.service.SimService;


//import com.vodafone.prepare.service.SimService;

@RestController
@CrossOrigin(origins="*")
@RequestMapping(value={"/","/activateSim"})
public class ActivateSimController {

	private static final Logger log = LoggerFactory.getLogger(ActivateSimController.class);
	
	@Autowired
	ActivateSimService activateSimService;
	
		
	StatusResponseWithMessage srwm = new StatusResponseWithMessage();
	
		
	  @PostMapping(value="activateSimImpl")
	  public StatusResponseWithMessage activateSim(@RequestBody Sim sim) throws Exception{
		
		log.info("inside activateSim......");
   		srwm = activateSimService.activateSim(sim);
		 return srwm;
	  }
	
		 
	  
	
}
