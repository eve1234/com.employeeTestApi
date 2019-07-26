package com.employeeapi.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC001_Get_All_Employees extends TestBase {
	
	@BeforeClass
	void getAllEmployees() throws InterruptedException {
		
		logger.info("*********Started  TC001_Get_All_Employees  **********");
		
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		httpRequest =RestAssured.given();
		response =httpRequest.request(Method.GET, "/employees");
		
		Thread.sleep(3);
	}
	
	@Test
	void checkResponseBody() {
		logger.info("*********Started  TC001_Get_All_Employees - checking response body **********");
		
		String responseBody = response.getBody().asString();
		logger.info("Response Body ==>" + responseBody);
		Assert.assertTrue(responseBody!=null);
	}
	
	@Test
	void checkStatusCode() {
		logger.info("*********Started  TC001_Get_All_Employees - checking status code"
				+ " **********");
		
		int statusCode = response.getStatusCode();// Getting status code
		logger.info("Status Code is  **" + statusCode);
		Assert.assertEquals(statusCode, 200);
		
			
		}
	
		
	@Test
	void checkRespomseTime() {
		logger.info("*********Started  TC001_Get_All_Employees - checking response time"
				+ " **********");
		
		long responseTime =response.getTime();
		
		logger.info("respomse Time is ** " + responseTime );
		
		if(responseTime<2000) {
			logger.info("Response Time is greater than 20000");
			Assert.assertTrue(responseTime<10000);
		}
		
	}
	
	@Test
	void checkStatusLine() {
		logger.info("*********Started  TC001_Get_All_Employees - checking Sataus Line **********");
		String statusLine =response.getStatusLine();
		logger.info("status Line is ** " + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}
	
	@Test
	void checkContentType() {
		logger.info("*********Started  TC001_Get_All_Employees - checking Content Type **********");
		String contentType=response.getContentType();
		logger.info(" content Type is ** " + contentType);
		Assert.assertEquals(contentType, "text/html; charset=UTF-8");
		
	}
	
	@Test
	void checkServerType() {
		logger.info("*********Started  TC001_Get_All_Employees - checking Server Type **********");
		String serverType=response.getHeader("Server");
		logger.info(" server Type is ** " + serverType);
		Assert.assertEquals(serverType, "nginx/1.14.1");
	}
	
	@Test
	void checkContentEncoding() {
		logger.info("*********Started  TC001_Get_All_Employees - checking Content Encoding **********");
		String contentEncoding=response.getHeader("Content-Encoding");
		logger.info(" Encoding is ** " + contentEncoding);
		Assert.assertEquals(contentEncoding, "gzip");
		
	}

	@Test
	void checkContentLength() {
		logger.info("*********Started  TC001_Get_All_Employees - checking Content Length **********");
		String contentLength=response.getHeader("Content-Length");
		logger.info(" content Length is  ** " + contentLength);
		
		if(Integer.parseInt(contentLength)<100)
			logger.warn("Content length is less than 100");
		Assert.assertTrue(Integer.parseInt(contentLength)>100);
	
	
	}
	
	@Test
	void checkCookies() {
		logger.info("*********Started  TC001_Get_All_Employees - checking Cookies **********");
		String cookies=response.getCookie("PHPSESSID");
		logger.info("cookies is ** " + cookies);
	}
	
	@AfterClass
	void tearDown()
	{
		logger.info("*********Finishing  TC001_Get_All_Employees **********************");
		
	}
}
