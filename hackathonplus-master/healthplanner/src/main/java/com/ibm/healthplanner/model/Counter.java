package com.ibm.healthplanner.model;
/**
 * @author DivyaKV
 * a sequence generated to use in id
 * 
 */
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "counter")
public class Counter {
	
	private String _id = "user_id";
	private int seq_value;
	
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public int getSeq_value() {
		return seq_value;
	}
	public void setSeq_value(int seq_value) {
		this.seq_value = seq_value;
	}
	public Counter(String _id, int seq_value) {
		super();
		this._id = _id;
		this.seq_value = seq_value;
	}
	

	
}
