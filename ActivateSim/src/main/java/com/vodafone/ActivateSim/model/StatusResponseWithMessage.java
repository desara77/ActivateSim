package com.vodafone.ActivateSim.model;

public class StatusResponseWithMessage {
	
	private int resultCode;
	private String resultDescription;
	private MessageWithParams messagewithparams;
	public int getResultCode() {
		return resultCode;
	}
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
	public String getResultDescription() {
		return resultDescription;
	}
	public void setResultDescription(String resultDescription) {
		this.resultDescription = resultDescription;
	}
	public MessageWithParams getMessagewithparams() {
		return messagewithparams;
	}
	public void setMessagewithparams(MessageWithParams messagewithparams) {
		this.messagewithparams = messagewithparams;
	}
	
	public void createOkResult() {
		this.setResultCode(0);
		this.setResultDescription("Activate Service terminated successfully");
	}

	public void addErrorMesge(String msdcode, String methodname) {
		
		this.setResultCode(9999);
		this.setResultDescription(methodname + " terminated with Error");
		MessageWithParams msg = new MessageWithParams();
		msg.setMessageDescription(msdcode);
		this.setMessagewithparams(msg);
		
	}

}
