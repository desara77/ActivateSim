package com.vodafone.ActivateSim.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.vodafone.ActivateSim.model.StatusResponseWithMessage;
@Configuration
@ControllerAdvice
public class GlobalExceptionHandler {
	static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	@ExceptionHandler({Exception.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
    public String handleException(Exception ex) {
        Map<String,Object> map = new HashMap<String, Object>();
        StatusResponseWithMessage response =new StatusResponseWithMessage();
        log.error(ex.getLocalizedMessage());
        response.setResultDescription(ex.getLocalizedMessage());
        response.setResultCode(1083);
        map.put("error", response.getResultDescription());
        map.put("errorcode", response.getResultCode());
        map.put("status", "KO");
        map.put("errortype", "ALERT");
        return map.toString();
	
    }

}
