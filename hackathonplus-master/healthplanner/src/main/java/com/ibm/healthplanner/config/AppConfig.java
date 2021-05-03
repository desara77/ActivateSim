package com.ibm.healthplanner.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;


@Configuration
@EnableWebMvc
@EnableScheduling
@ComponentScan("com.ibm.healthplanner")
@PropertySource("classpath:application.properties")
public class AppConfig implements WebMvcConfigurer{


	@Value("${mongo.connection.string}")
	private String connectionString;
	
	@Value("${mongo.db.name}")
	private String dbName;
	
		
	@Bean
	public MongoClient mongoClient()
	{
	     //return MongoClients.create("mongodb://localhost:27017");
		return MongoClients.create(connectionString);
	}

	public @Bean MongoTemplate mongoTemplate() {
	    //return new MongoTemplate(mongoClient(), "healthplannerdb");
	    return new MongoTemplate(mongoClient(), dbName); 
	}
	
	
	
}
