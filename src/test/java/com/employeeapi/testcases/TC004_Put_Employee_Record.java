package com.employeeapi.testcases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeTestApi.utilities.RestUtils;
import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC004_Put_Employee_Record extends TestBase{
	
	RequestSpecification httpRequest;
	Response response;
	//ransom utility file to provide random data.
		//need dynamic data
		String empName=RestUtils.empName();
		String empSalary=RestUtils.empSal();
		String empAge=RestUtils.empAge();
		
		@BeforeClass
		void createEmployee() throws InterruptedException {
			logger.info("*******  Started TC003_Post_Employee_Records *******");
			RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
			httpRequest = RestAssured.given();
			
			
			//JSONOBJECT is a class that represents a simple JSON. We can add key-value 
			//pairs using the put method
			//{"name": "John123X", "salary": "123", "age":"23"}
			JSONObject requestParams = new JSONObject();
			requestParams.put("name", empName);//cast
			requestParams.put("salary", empSalary);//cast
			requestParams.put("age", empAge);//cast
			
			
			//Add a header stating the Request body is a JSON
			httpRequest.header("Content-Type", "application/json");
			
			
			//Add the Json to the body of the request
			httpRequest.body(requestParams.toJSONString());
		
			response =httpRequest.request(Method.PUT, "/update/"+empID);
			
			Thread.sleep(5000);
			//give system time to verify with following test mehtods
		}
		
		@Test
		void checkResponeBody() {
			logger.info("*******  Started TC003_Post_Employee_Records -- check Respone Body *******");
			String responseBody = response.getBody().asString();
			Assert.assertEquals(responseBody.contains(empName), true);
			Assert.assertEquals(responseBody.contains(empSalary), true);
			Assert.assertEquals(responseBody.contains(empAge), true);
			
			
		}
		
		@Test
		void checkStatusCode() {
			logger.info("*******  Started TC003_Post_Employee_Records -- check Status Code *******");
			int statusCode =response.getStatusCode(); //Getting status code
			Assert.assertEquals(statusCode, 200);
			
		}
		
		@Test
		void checkStatusLine() {
			logger.info("*******  Started TC003_Post_Employee_Records -- check Status Line *******");
			String statusLine = response.getStatusLine(); //Getting status line
			Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
			
		}
		
		@Test
		void checkContentType() {
			logger.info("*******  Started TC003_Post_Employee_Records -- check Content Type *******");
			String contentType =response.header("Content-Type"); // Getting status code
			Assert.assertEquals(contentType, "text/html; charset=UTF-8");
			
		}
		
		@Test
		void checkServerType() {
			logger.info("*******  Started TC003_Post_Employee_Records -- check Server Type *******");
			String serverType =response.header("Server");
			Assert.assertEquals(serverType, "nginx/1.14.1");
			
		}
		
		@Test
		void checkContentEncoding() {
			logger.info("*******  Started TC003_Post_Employee_Records -- check Content Encoding *******");
			String contentEncoding = response.header("Content-Encoding");
			Assert.assertEquals(contentEncoding, "gzip");
		
		}
		
		@AfterClass
		void tearDown() {
			logger.info("*******  Started TC003_Post_Employee_Records --finishing *******");
		}
	
}
