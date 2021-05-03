package com.ibm.healthplanner.dao;

import com.ibm.healthplanner.model.CalorieBurn;

public interface CalorieBurnDAO {


	public void saveCalorieRecord(CalorieBurn calorie, String collectionName);
	public CalorieBurn getCalorieRecord(String id,String collectionName);
	public void deleteCalorieCollection(String collectionName);
	public CalorieBurn getRecordbyDate(String date, String collectionName);
	public void deleteCalorieRecord(String id, String collectionName);

}
