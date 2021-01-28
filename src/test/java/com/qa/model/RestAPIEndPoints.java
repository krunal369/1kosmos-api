package com.qa.model;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAPIEndPoints {
	
	//Declare Request variable
	private final RequestSpecification httpRequest;
	public RestAPIEndPoints(String BaseUrl) {
		// define variable and respective header for request.
		RestAssured.baseURI = BaseUrl;
		httpRequest = RestAssured.given();		 
		httpRequest.header("Content-Type", "application/json");
	}
	public Response gethealthz() {
		return httpRequest.get(Route.getRoute1());
	}
	public Response getEULA(String Key,String value) {
		return httpRequest.queryParam(Key,value).get(Route.getRoute2());
	}
}
