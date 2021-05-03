package com.ibm.healthplanner.model;
/**
 * 
 * @author DivyaKV
 *table to save calorie consumption records.
 *The collection is created as a capped collection.
 *
 */

public class CalorieBurn extends BaseEntity{
	
	private String RunCalorie;
	private String WalkCalorie;
	private String CyclingCalorie;
	private String OtherCalorie;
	private String InactiveCalorie;
	private String TotalCalorie;
	private String updated_date;
	
	public String getRunCalorie() {
		return RunCalorie;
	}

	public void setRunCalorie(String runCalorie) {
		RunCalorie = runCalorie;
	}

	public String getWalkCalorie() {
		return WalkCalorie;
	}

	public void setWalkCalorie(String walkCalorie) {
		WalkCalorie = walkCalorie;
	}

	public String getCyclingCalorie() {
		return CyclingCalorie;
	}

	public void setCyclingCalorie(String cyclingCalorie) {
		CyclingCalorie = cyclingCalorie;
	}

	public String getOtherCalorie() {
		return OtherCalorie;
	}

	public void setOtherCalorie(String otherCalorie) {
		OtherCalorie = otherCalorie;
	}

	public String getInactiveCalorie() {
		return InactiveCalorie;
	}

	public void setInactiveCalorie(String inactiveCalorie) {
		InactiveCalorie = inactiveCalorie;
	}

	public String getTotalCalorie() {
		return TotalCalorie;
	}

	public void setTotalCalorie(String totalCalorie) {
		TotalCalorie = totalCalorie;
	}

	public String getUpdated_date() {
		return updated_date;
	}

	public void setUpdated_date(String updated_date) {
		this.updated_date = updated_date;
	}
	
	public CalorieBurn(String runCalorie, String walkCalorie, String cyclingCalorie, String otherCalorie,
			String inactiveCalorie, String totalCalorie, String updated_date) {
		super();
		RunCalorie = runCalorie;
		WalkCalorie = walkCalorie;
		CyclingCalorie = cyclingCalorie;
		OtherCalorie = otherCalorie;
		InactiveCalorie = inactiveCalorie;
		TotalCalorie = totalCalorie;
		this.updated_date = updated_date;
	}

	public CalorieBurn() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "CalorieBurn [RunCalorie=" + RunCalorie + ", WalkCalorie=" + WalkCalorie + ", CyclingCalorie="
				+ CyclingCalorie + ", OtherCalorie=" + OtherCalorie + ", InactiveCalorie=" + InactiveCalorie
				+ ", TotalCalorie=" + TotalCalorie + ", updated_date=" + updated_date + ", getId()=" + getId()
				+ ", toString()=" + super.toString() +  "]";
	}

	
	
	
	

}
