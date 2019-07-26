package com.employeeTestApi.utilities;

import org.apache.commons.lang3.RandomStringUtils;

public class RestUtils {
	
	//use static methods to access the object
	//using class name directly
	public static String empName() {
		String generatedString =RandomStringUtils.randomAlphabetic(1);
		return ("John"+generatedString);
	}
	
	public static String empSal() {
		String generatedString =RandomStringUtils.randomAlphabetic(5);
		return (generatedString);
		
	}

	public static String empAge() {
		String generatedString = RandomStringUtils.randomNumeric(2);
		return (generatedString);
	}
}
