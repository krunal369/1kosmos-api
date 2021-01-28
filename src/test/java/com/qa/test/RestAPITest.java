

package com.qa.test;

import static org.hamcrest.CoreMatchers.notNullValue;

import java.io.FileNotFoundException;
import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.github.fge.jsonschema.SchemaVersion;
import com.github.fge.jsonschema.cfg.ValidationConfiguration;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.qa.model.ConfigReader;
import com.qa.model.HTTPStatusCodes;
import com.qa.model.RestAPIEndPoints;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class RestAPITest {
	public Response response;
	public RestAPIEndPoints endPoints;
	public String newlyAddedId;
	
	@BeforeMethod
	public void setUp() throws FileNotFoundException {
		Properties property=ConfigReader.readPropertyFileValue();		
		endPoints = new RestAPIEndPoints(property.getProperty("baseUrl"));
	}
	
	@Test
	public void RestAPITest1() {
		endPoints.gethealthz().then().assertThat().statusCode(HTTPStatusCodes.HTTPSOK);
	}
	
	@Test
	@Parameters({"key","value","validateResponseFile"})
	public void RestAPITest2(String key, String value, String validateResponseFile) {
		JsonSchemaFactory jsonSchemaFactory = JsonSchemaFactory.newBuilder().setValidationConfiguration(ValidationConfiguration.newBuilder().setDefaultVersion(SchemaVersion.DRAFTV4).freeze()).freeze();
		endPoints.getEULA(key,value).then()
		.statusCode(HTTPStatusCodes.HTTPSOK)
		.assertThat()
		.body(notNullValue())
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath(validateResponseFile).using(jsonSchemaFactory));
	}
}
