package com.employeeapi.testcases;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.AssertJUnit;
import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.AfterClass;



public class TC002_Get_Single_Employee_Record extends TestBase {

	
	RequestSpecification httpRequest;
	Response response;
	
	@BeforeClass
	void getEmployeeeData() throws InterruptedException {
		
		logger.info("*********  Started TC002_Get_Single_employee_Record  **********");
		
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1/";
		httpRequest =RestAssured.given();
		response =httpRequest.request(Method.GET, "/employee/"+empID);
		
		Thread.sleep(3);
		

	}
	@Test
	void checkResponseBody() {
		logger.info("*********  Started TC002_Get_Single_employee_Record  - check Response Body  **********");
		String responseBody =response.getBody().asString();
		Assert.assertEquals(responseBody.contains(empID), true);
		
	}
	
	@Test
	void checkStatusCode() {
		logger.info("*********  Started TC002_Get_Single_Employee_Record  - check Status Code  **********");
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
	}
	
	
	@Test
	void checkResponseTime() {
		logger.info("*********  Started TC002_Get_Single_Employee_Record  - check Response Time  **********");
		long responseTime = response.getTime();
		Assert.assertTrue(responseTime<6000);
	}
	
	@Test
	void checkStatusLine() {
		logger.info("*********  Started TC002_Get_Single_Employee_Record  - check Status Line  **********");
		String statusLine =response.getStatusLine();
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	
	}
	
	@Test
	void checkContentType() {
		logger.info("*********  Started TC002_Get_Single_Employee_Record  - check Content Type  **********");
		String contentType= response.header("Content-Type");
		Assert.assertEquals(contentType, "text/html; charset=UTF-8");
		
	}
	
	@Test
	void checkServerType() {
		logger.info("*********  Started TC002_Get_Single_Employee_Record  - check Server Type **********");
		String serverType =response.header("Server");
		Assert.assertEquals(serverType, "nginx/1.14.1");
		
	}
	
	@Test
	void checkContentLength() {
		logger.info("*********  Started TC002_Get_Single_Employee_Record  - check Content Length  **********");	
		String contentLength = response.header("Content-Length");
		Assert.assertTrue(Integer.parseInt(contentLength)<1500);
	
	}

	@AfterClass
	void tearDown() {
		logger.info("********* finishing TC002_Get_Single_Employee_Record  **********");	
	}
}