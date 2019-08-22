package com.application.testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.framework.reUsableComponents.TestNgMethods;

import io.restassured.response.Response;


public class TC01_CreateUser extends TestNgMethods{
	@BeforeTest
	public void setData() {
		testCaseName="TC01_CreateUser";
		testDescription="Create user using web service";
		testNodes="Create";
		category="Smoke";
		authors="Arul";
		dataSheetName="TC01";
	}
    @Test(dataProvider="fetchData") // Creating user by posting the required details like request body, request headers 
	public void createUser(String strEndpoint,String strHttpMethod,String strParameters,String strHeaders, String strAuthorization, String strRequestBody, String strUserID) {

    	try
    	{
    		Response response =sendRequest(strEndpoint, strHttpMethod, strParameters, strHeaders, strAuthorization, strRequestBody);
    		//Response validation
    		if(response!=null)
    		{
    			String userId = 
    					response.
    					then().statusCode(200).extract().path("user_id");
    			if(!userId.isEmpty())
    			{
    				reportStep("User Id created is "+userId, "Pass");
    			}else
        		{
        			reportStep("User Id is empty", "Fail");
        		}
    			
    		}else
    		{
    			reportStep("Response is null", "Fail");
    		}
    		
    	}catch(Exception ex)
    	{
    		reportStep("Create User failed due to "+ex.toString(), "Fail");
    	}
	}
    
    
}
