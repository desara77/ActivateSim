package com.ibm.healthplanner.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

	@Document
	public abstract class BaseEntity {
	    @Id
	    private String id;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		@Override
		public String toString() {
			return "BaseEntity [id=" + id + "]";
		}
	    

	}
