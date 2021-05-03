package com.vodafone.ActivateSim.service;

//@SuppressWarnings("serial")
public class ServiceException extends RuntimeException {

	
	public ServiceException(){
		super();
	}
	public ServiceException(String msg){
		super(msg);
	}	
	
	public ServiceException(Throwable ex){
		super(ex);
	}	
	public ServiceException(String msg,Throwable ex){
		super(msg,ex);
	}	
}
