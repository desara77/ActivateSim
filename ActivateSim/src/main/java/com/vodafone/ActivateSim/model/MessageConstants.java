package com.vodafone.ActivateSim.model;

import org.springframework.stereotype.Component;

@Component
public class MessageConstants {
	

	public static final String NUMBER_NOT_VALID_WITH_LENGTH = "The input Mobile number is not Valid and it shoud contain 10 Digits";
	public static final String NUMBER_CONTAINS_ILLEGAL_CHARS = "The mobile is not proper which contains illegal characters";
	public static final String SIM_ACTIVATED_ALREADY = "The Sim is already in active status";
	public static final String SIM_CANNOT_BE_ACTIVATED = "The Sim cannot be activated with the present status it should be assigned";
}
