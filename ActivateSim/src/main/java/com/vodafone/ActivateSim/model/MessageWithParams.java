package com.vodafone.ActivateSim.model;

public class MessageWithParams {
	
	private int messageCode;
	private String messageDescription;
	private Params paramObj;
	public int getMessageCode() {
		return messageCode;
	}
	public void setMessageCode(int messageCode) {
		this.messageCode = messageCode;
	}
	public String getMessageDescription() {
		return messageDescription;
	}
	public void setMessageDescription(String messageDescription) {
		this.messageDescription = messageDescription;
	}
	public Params getParamObj() {
		return paramObj;
	}
	public void setParamObj(Params paramObj) {
		this.paramObj = paramObj;
	}
	
	
	

}
