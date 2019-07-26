package com.employeeapi.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class TC005_Delete_Employee_Record extends TestBase {

	RequestSpecification httpRequest;
	Response response;
	
	@BeforeClass
	void deleteEmployee() throws InterruptedException {
		logger.info("********  Started TC005_Delete_Employee_Record **********");
	
		RestAssured.baseURI ="http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		
		response= httpRequest.request(Method.GET, "/employees");
		
		//First get the JSONPath object instance from the response interface
		JsonPath jsonPathEvaluator = response.jsonPath();
		
		//capture id
		String empID =jsonPathEvaluator.get("[0].id");
		response =httpRequest.request(Method.DELETE, "/delete/" +empID);// Pass ID to delete record
		
		Thread.sleep(3000);
		
	
	}
	
	@Test
	void checkResponseBody() {
		logger.info("********  Started TC005_Delete_Employee_Record  --- check Response Body**********");
		String responseBody =response.getBody().asString();
		Assert.assertEquals(responseBody.contains("successfully! deleted Records"), true);
		
	}
	
	@Test
	void checkStatusCode() {
		logger.info("********  Started TC005_Delete_Employee_Record  --- check Status Code -- **********");
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
	}
	
	@Test
	void checkStatusLine() {
		logger.info("********  Started TC005_Delete_Employee_Record  --- check Status Line -- **********");
		String statusLine = response.getStatusLine();
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	
	}
	
	@Test
	void checkContentType() {
		logger.info("*******  Started TC005_Delete_Employee_Record -- check Content Type *******");
		String contentType =response.header("Content-Type"); // Getting status code
		Assert.assertEquals(contentType, "text/html; charset=UTF-8");
		
	}
	
	@Test
	void checkServerType() {
		logger.info("*******   TC005_Delete_Employee_Record -- check Server Type *******");
		String serverType =response.header("Server");
		Assert.assertEquals(serverType, "nginx/1.14.1");
		
	}
	
	@Test
	void checkContentEncoding() {
		logger.info("*******  Started TC005_Delete_Employee_Record -- check Content Encoding *******");
		String contentEncoding = response.header("Content-Encoding");
		Assert.assertEquals(contentEncoding, "gzip");
	
	}
	
	@AfterClass
	void tearDown() {
		logger.info("*******  TC005_Delete_Employee_Record --finishing *******");
	}

}
