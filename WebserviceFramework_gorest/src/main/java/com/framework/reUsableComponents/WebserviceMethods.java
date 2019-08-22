package com.framework.reUsableComponents;

import java.util.HashMap;
import java.util.Map;


import com.framework.utils.Reporter;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class WebserviceMethods extends Reporter implements Webservice_Interface{

	
	public WebserviceMethods() {

	}


	public Response sendRequest(String strEndpoint, String strHttpMethod, String strParameters, String strHeaders, String strAuthorization,
			String strRequestBody) throws Exception {
		Response response = null;
		//Creating httprequest object
		RequestSpecification httpRequest =RestAssured.given();
		
		//Assigning parameters based on input
		if(!strParameters.isEmpty())
		{
			String[] arrOfParams = strParameters.split(";");
			final Map<String, String> mapOfParams = new HashMap<String, String>();
			for(int iCount=0;iCount<arrOfParams.length;iCount++)
			{
				mapOfParams.put(arrOfParams[iCount], arrOfParams[iCount+1]);
				iCount++;
			}
			httpRequest.pathParams(mapOfParams);
		}
		
		//Assigning headers based on input
		if(!strHeaders.isEmpty())
		{
			String[] arrOfHeaders = strParameters.split(";");
			final Map<String, String> mapOfHeaders = new HashMap<String, String>();
			for(int iCount=0;iCount<arrOfHeaders.length;iCount++)
			{
				mapOfHeaders.put(arrOfHeaders[iCount], arrOfHeaders[iCount+1]);
				iCount++;
			}
			httpRequest.headers(mapOfHeaders);
		}
		//Assigning authorization header based on input
		if(!strAuthorization.isEmpty())
		{
			httpRequest.headers("Authorization",strAuthorization);
		}
		//Assigning httpmethod based on input
		switch(strHttpMethod.toLowerCase())
		{
		case "get":
			httpRequest.request(Method.GET);
			response=httpRequest.get(strEndpoint);
			break;
		case "post":
			httpRequest.request(Method.POST);
			httpRequest.body(strRequestBody);
			response=httpRequest.post(strEndpoint);
			break;
		}
		System.out.println("Response\n" + response.asString());
		return response;
	}



	





}
