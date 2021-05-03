package com.vodafone.ActivateSim.model;

import java.math.BigDecimal;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

//@SuppressWarnings("serial")
@Document(collection = "Sim")
public class Sim {
	
	@JsonProperty("Sim_Id")
	String simId;
	@JsonProperty("Customer_Id")
	BigDecimal customerId;
	@JsonProperty("Msisdn_Num")
	BigDecimal msisdnNum;
	@JsonProperty("Sim_Num")
	String simNum;
	@JsonProperty("Status")
	String status;
	@JsonProperty("Reason")
	String reason;
	@JsonProperty("TMCode")
	String tmcode;
	
	public String getSimId() {
		return simId;
	}
	public void setSimId(String simId) {
		this.simId = simId;
	}
	public BigDecimal getCustomerId() {
		return customerId;
	}
	public void setCustomerId(BigDecimal customerId) {
		this.customerId = customerId;
	}
	public BigDecimal getMsisdnNum() {
		return msisdnNum;
	}
	public void setMsisdnNum(BigDecimal msisdnNum) {
		this.msisdnNum = msisdnNum;
	}
	public String getSimNum() {
		return simNum;
	}
	public void setSimNum(String simNum) {
		this.simNum = simNum;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getTmcode() {
		return tmcode;
	}
	public void setTmcode(String tmcode) {
		this.tmcode = tmcode;
	}
	
	
	

}
