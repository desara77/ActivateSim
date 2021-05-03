package com.ibm.healthplanner.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.ibm.healthplanner.model.Counter;

/**
 * 
 * @author DivyaKV
 *db.createCollection("counter")
 *db.counter.insert ({_id:"user_id",seq_value : 0})
 */

@Repository
public class CounterDAOImpl implements CounterDAO {
	
	@Autowired
    private MongoTemplate mongoTemplate;
    
    public static final String COLLECTION_NAME = "counter";
    public static final String ID_KEY ="user_id";
    
    public int getNextId() {
    	
    	Query query = new Query();
    	query.addCriteria(Criteria.where("_id").is(ID_KEY));

    	Update update = new Update();
        update.inc("seq_value", 1);
        
        FindAndModifyOptions options = new FindAndModifyOptions();
        options.returnNew(true);

        Counter count = mongoTemplate.findAndModify(query, update, options, Counter.class, COLLECTION_NAME);
        
    	return count.getSeq_value();
    }

}
