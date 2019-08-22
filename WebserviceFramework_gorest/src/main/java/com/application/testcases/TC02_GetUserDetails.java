package com.application.testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.framework.reUsableComponents.TestNgMethods;

import io.restassured.response.Response;


public class TC02_GetUserDetails extends TestNgMethods{
	@BeforeTest
	public void setData() {
		testCaseName="TC02_GetUserDetails";
		testDescription="Get user details using web service";
		testNodes="GetUser";
		category="Smoke";
		authors="Arul";
		dataSheetName="TC01";
	}
    @Test(dataProvider="fetchData") //Getting the user details with user id and request headers  
	public void validateUserDetails(String strEndpoint,String strHttpMethod,String strParameters,String strHeaders, String strAuthorization, String strRequestBody, String strUserID) {
    	try
    	{
    		System.out.println("requestbody :"+strRequestBody);
    		Response response =sendRequest(strEndpoint+"/"+ strUserID, strHttpMethod, strParameters, strHeaders, strAuthorization, strRequestBody);
    		//Response validation
    		if(response!=null)
    		{
    			String strUserdetails = 
    					response.getBody().asString();
    			if(!strUserdetails.isEmpty())
    			{
    				if(strUserdetails.equalsIgnoreCase(strRequestBody))
    				{
    					reportStep("User details are displayed as expected", "Pass");
    				}else
    				{
    					reportStep("User details are not equal. Expected is \""+strRequestBody+"\", but actual is \""+strUserdetails+"\"", "Fail");
    				}
    				
    			}else
        		{
        			reportStep("User details is empty", "Fail");
        		}
    		}else
    		{
    			reportStep("Response is null", "Fail");
    		}
    	}catch(Exception ex)
    	{
    		reportStep("Validate User failed due to "+ex.toString(), "Fail");
    	}
   		
	}

}
