package com.ibm.healthplanner.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.healthplanner.dao.CalorieBurnDAO;
import com.ibm.healthplanner.model.CalorieBurn;
import com.ibm.healthplanner.model.CalorieBurnRequest;
import com.ibm.healthplanner.model.Patient;

/**
 * 
 * @author DivyaKV
 * all calorie burn related operations
 * 
 */

@Service
public class CalorieServiceImpl implements CalorieService {
	
	private static final Logger log = LoggerFactory.getLogger(CalorieServiceImpl.class);
	
	@Autowired
	CalorieBurnDAO calorieBurnDao;
	
	@Autowired
	PatientService patientService;
	
	@Value("${nutritionix.api.url}")
	private String exerciseApiUrl ;
	
	@Value("${nutritionix.app.id}")
	private String appId;
	
	@Value("${nutritionix.app.key}")
	private String appKey;
	
	@Value("${nutritionix.remote.user.id}")
	private String userId;
	
	
	@Override
	public String recordCalorieBurned(String id, String exercise) {
		
		Patient patient = patientService.getPatientbyid(id);
		CalorieBurnRequest calorieRequest = setApiRequest(patient, exercise);
		String apiRequest = getJsonRequest(calorieRequest);
		ResponseEntity<String> apiResponse = getCalorie(apiRequest);
		HashMap<String, String> activity_calorie = getcaloriefromResponse(apiResponse);
		String record = saveRecord(activity_calorie,patient);
		return record;
	}
	
	@Override
	public CalorieBurnRequest setApiRequest(Patient patient, String exercise) {
		
		CalorieBurnRequest  calorieRequest = new CalorieBurnRequest();
		
		calorieRequest.setQuery(exercise);
		calorieRequest.setGender(patient.getGender());
		calorieRequest.setAge(patient.getAge());
		calorieRequest.setHeight_cm(patient.getHeight());
		calorieRequest.setWeight_kg(patient.getWeight());
		
		return calorieRequest;
		
	}

	@Override
	public String getJsonRequest(CalorieBurnRequest request) {
		ObjectMapper mapper = new ObjectMapper();
	    String jsonRequest = null;
	    try {
	      jsonRequest = mapper.writeValueAsString(request);
	     log.info("ResultingJSONstring = " + jsonRequest);
	    } catch (JsonProcessingException e) {
	       e.printStackTrace();
	    }
	    return jsonRequest;
	}

	@Override
	public ResponseEntity<String> getCalorie(String apiRequest) {
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type","application/json");
		headers.add("x-app-id", appId);
		headers.add("x-app-key", appKey);
		headers.add("x-remote-user-id",userId);
		HttpEntity<String> request = new HttpEntity<>(apiRequest,headers);
		ResponseEntity<String> calorie =null;
		
		calorie = restTemplate.postForEntity(exerciseApiUrl, request, String.class);
		log.info("calorie : "+ calorie);
		return calorie;
	}

	@Override
	public HashMap<String, String> getcaloriefromResponse(ResponseEntity<String> response) {
		
		HashMap<String, String> activity_calorie = new HashMap<String, String>();
		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode node = mapper.readTree(response.getBody());
			String calorieResp = node.findValue("nf_calories").asText();
			String activity = node.findValue("name").asText();
			activity_calorie.put("activity", activity);
			activity_calorie.put("calorie",calorieResp);
			log.info("calorieBurned : "+ calorieResp);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	
		}
		return activity_calorie;
	}

	@Override
	public String saveRecord(HashMap<String, String> activity_calorie, Patient patient) {
		CalorieBurn calorieDB, calorie = null;
		//setcalorieRecord
		String r_id = "R" + patient.getId() + getDate();
		calorieDB = calorieBurnDao.getCalorieRecord(r_id, patient.getCalorieCollection());
		calorie = calorieDB==null 
				? getFirstCalorieRecord(patient)
				: calorieDB;
				
		//update the as per new exercise
		if(activity_calorie!=null) {
			String activity = activity_calorie.get("activity");
			String calorieBurn = activity_calorie.get("calorie");
			
			BiFunction<String, String, Double> addCalFunction = (cal1,cal2)-> 
															Double.parseDouble(cal1)+Double.parseDouble(cal2);

			switch (activity) {
			case "running":
				Double runCalorie = addCalFunction.apply(calorie.getRunCalorie(),calorieBurn);
				calorie.setRunCalorie(getFormatted(runCalorie));
				break;
			case "walking":
				Double walkCalorie = addCalFunction.apply(calorie.getWalkCalorie(),calorieBurn);
				calorie.setWalkCalorie(getFormatted(walkCalorie));
				break;
			case "road cycling":
				Double cyclingCalorie = addCalFunction.apply(calorie.getCyclingCalorie(),calorieBurn);
				calorie.setCyclingCalorie(getFormatted(cyclingCalorie));
				break;
			default:
				Double otherCalorie = addCalFunction.apply(calorie.getOtherCalorie(),calorieBurn);
				calorie.setOtherCalorie(getFormatted(otherCalorie));
				break;
			}
			
	}
		
		int elapsedHr = LocalTime.now().getHour();
		Double inactive = (patient.getBmr()/24)*elapsedHr;
		String inactiveCalorie = getFormatted(inactive);
		calorie.setInactiveCalorie(inactiveCalorie);
		
		Double totCalorie = Double.parseDouble(calorie.getRunCalorie()) + 
				              Double.parseDouble(calorie.getWalkCalorie()) + 
				              Double.parseDouble(calorie.getCyclingCalorie())+
				              Double.parseDouble(calorie.getOtherCalorie())+
				              Double.parseDouble(calorie.getInactiveCalorie());
		String totalCalorie = getFormatted(totCalorie);
		calorie.setTotalCalorie(totalCalorie);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = dateFormat.format(new Date());
		calorie.setUpdated_date(date);
		
		calorieBurnDao.saveCalorieRecord(calorie, patient.getCalorieCollection());
		log.info(calorie.toString());
		return calorie.toString();
		
	}
	
	public String getFormatted(Double value) {
		
		DecimalFormat df = new DecimalFormat("000000.00");
		BigDecimal valueBig = new BigDecimal(value);
		
		valueBig = valueBig.setScale(2, RoundingMode.HALF_UP);
		String valueRet = df.format(valueBig);
		return valueRet;
		
	}
	
	public CalorieBurn getFirstCalorieRecord(Patient patient) {
		
		String value = "000000.00";
		
		CalorieBurn record = new CalorieBurn();
		String r_id = "R" + patient.getId() + getDate();
		record.setId(r_id);
		
		record.setRunCalorie(value);
		record.setWalkCalorie(value);
		record.setCyclingCalorie(value);
		record.setOtherCalorie(value);
		record.setInactiveCalorie(value);
		record.setTotalCalorie(value);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = dateFormat.format(new Date());
		record.setUpdated_date(date);
		return record;
	}
	
	public void deleteCalorieCollection(String collectionName) {
		calorieBurnDao.deleteCalorieCollection(collectionName);
	}
	
	public void deleteUserCollection(String patientId) {
		Patient patient = patientService.getPatientbyid(patientId);
		calorieBurnDao.deleteCalorieCollection(patient.getCalorieCollection());
	}
	
	
	@Override
	public void deleteRecordbyDate(String date,String collectionName) {
		
		CalorieBurn record = calorieBurnDao.getRecordbyDate(date,collectionName);//change
		if (record!=null)
				calorieBurnDao.deleteCalorieRecord(record.getId(), collectionName);
	}
	
	private String getDate() {
		
		Function<Date,String> getDayFunction = (d)-> new SimpleDateFormat("dd").format(d);
		return getDayFunction.apply(new Date());
		
	}
	
	//creating first record everyday with schedule
	
	@Scheduled(cron="0 0 1-23 * * ?")//every hour except midnight
	private void updateInactiveCalorie() {
		
		List<Patient> patientlist = patientService.listPatient();
		patientlist.parallelStream().forEach(patient ->{ 
			saveRecord(null,patient);
		});

	    log.info("Inactive calorie is updated every 1 hour "+ new Date());
	}
	
	@Scheduled(cron="0 0 0 * * ?")//every midnight
	private void insertNewRecordDaily() {
		
		List<Patient> patientlist = patientService.listPatient();
		patientlist.parallelStream().forEach(patient -> {
			CalorieBurn calorieNew = getFirstCalorieRecord(patient);
			calorieBurnDao.saveCalorieRecord(calorieNew, patient.getCalorieCollection());
			log.info(calorieNew.toString());
		});

	    log.info("New record inserted everyday midnight "+ new Date());
		
	}

	
}
