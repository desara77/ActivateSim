package com.ibm.healthplanner.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.ibm.healthplanner.model.CalorieBurn;

/**
 * 
 * @author DivyaKV
 *
 */

@Repository
public class CalorieBurnDAOImpl implements CalorieBurnDAO {

	@Autowired
    private MongoTemplate mongoTemplate;
    
	@Override
	public void saveCalorieRecord(CalorieBurn calorie,String collectionName) {
		
		if(!mongoTemplate.collectionExists(collectionName)) {
			CollectionOptions collectionOptions = CollectionOptions.empty() ;
			collectionOptions.capped().size(10000).maxDocuments(7);
			mongoTemplate.createCollection(collectionName, collectionOptions);
		}
        mongoTemplate.save(calorie, collectionName);
	}    
    
	public CalorieBurn getCalorieRecord(String id,String collectionName) {
		CalorieBurn record =  mongoTemplate.findById(id, CalorieBurn.class, collectionName);
		if (null!=record)
			return record;
		else
			return null;
	}
	
	public void deleteCalorieCollection(String collectionName) {
		if(mongoTemplate.collectionExists(collectionName)) {
			mongoTemplate.dropCollection(collectionName);
		}
	}

	@Override
	public CalorieBurn getRecordbyDate(String date, String collectionName) {
		Query q = new Query();
		q.addCriteria(Criteria.where("updated_date").regex(date));
		CalorieBurn record =  mongoTemplate.findOne(q, CalorieBurn.class, collectionName);
		return record;
	}

	@Override
	public void deleteCalorieRecord(String id, String collectionName) {
		mongoTemplate.remove(new Query(Criteria.where("_id").is(id)),collectionName );
	}
}
