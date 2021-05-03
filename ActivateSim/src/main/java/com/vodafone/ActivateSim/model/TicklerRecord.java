package com.vodafone.ActivateSim.model;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class TicklerRecord {
	
	String ticklerCode;
	String ticklerStatus;
	Date createdDate;
	Date modifiedDate;
	Date closedDate;
	String shortDescription;
	BigDecimal simId;
	String msisdnNum;
	
	
	public String getTicklerCode() {
		return ticklerCode;
	}
	public void setTicklerCode(String ticklerCode) {
		this.ticklerCode = ticklerCode;
	}
	public String getTicklerStatus() {
		return ticklerStatus;
	}
	public void setTicklerStatus(String ticklerStatus) {
		this.ticklerStatus = ticklerStatus;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public Date getClosedDate() {
		return closedDate;
	}
	public void setClosedDate(Date closedDate) {
		this.closedDate = closedDate;
	}
	public String getShortDescription() {
		return shortDescription;
	}
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	public BigDecimal getSimId() {
		return simId;
	}
	public void setSimId(BigDecimal simId) {
		this.simId = simId;
	}
	public String getMsisdnNum() {
		return msisdnNum;
	}
	public void setMsisdnNum(String msisdnNum) {
		this.msisdnNum = msisdnNum;
	}
	
	
	

}
