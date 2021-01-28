package com.qa.model;

public class Route {
	private static final String route1 = "/healthz";
    private static final String route2 = "/api/v3/rest/default/eula";   
    
    public static String getRoute1() {
    	return route1;
    }
    public static String getRoute2() {
    	return route2;
    }
}
