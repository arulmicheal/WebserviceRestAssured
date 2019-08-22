package com.framework.reUsableComponents;

import io.restassured.response.Response;

public interface Webservice_Interface {
		/**
		 * Send the webservice request and returns the rest assured response
		 * @param strEndpoint - End point URL for the web service
		 * @param strHttpMethod - Http method (GET, POST)
		 * @param strParameters - Parameters to be passed for the web service request
		 * @param strHeaders - Request headers to be passed for the web service request
		 * @param strRequestBody - Request body to be passed for the web service request
		 * @return Rest assured response
		 * @throws Exception
		 */
		public Response sendRequest(String strEndpoint, String strHttpMethod, String strParameters, String strHeaders, String strAuthorization, String strRequestBody) throws Exception;


}



