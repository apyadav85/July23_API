package ets.RestAPIBase;

import java.util.Properties;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import ets.Resources.ProjectConfig;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class RestHttpMethods {
	private ProjectConfig projectconfig = new ProjectConfig();
	private Properties prop;
	private RequestSpecification requestspec = RestAssured.given();
	private Response response;
	
	public RequestSpecification createPostTokenRequest(String payload) throws Exception {
		requestspec
			.header("Content-Type", "application/json")
			.body(payload);
		return requestspec;
	}
	
	public RequestSpecification createPostRequest(String payload) throws Exception {
		prop = projectconfig.readConfig("Token");
		requestspec
			.header("Content-Type", "application/json")
			.header("x-auth-token", prop.getProperty("token"))
			.body(payload);
		return requestspec;
	}
	
	public RequestSpecification createPatchRequest(String payload) throws Exception {
		prop = projectconfig.readConfig("Token");
		requestspec
			.header("Content-Type", "application/json")
			.header("x-auth-token", prop.getProperty("token"))
			.body(payload);
		return requestspec;
	}
	
	public RequestSpecification createGetRequest() throws Exception {
		prop = projectconfig.readConfig("Token");
		requestspec
			.header("Content-Type", "application/json")
			.header("x-auth-token", prop.getProperty("token"));
		return requestspec;
	}
	
	public RequestSpecification createDeleteRequest() throws Exception {
		prop = projectconfig.readConfig("Token");
		requestspec
			.header("Content-Type", "application/json")
			.header("x-auth-token", prop.getProperty("token"));
		return requestspec;
	}
	
	public Response executePostRequest(RequestSpecification vJsonRequest, String vResource) throws Exception {
		prop = projectconfig.readConfig("Config");
		response = vJsonRequest.post(prop.getProperty("baseUrl") + vResource);
		return response;
	}
	
	public Response executeGetRequest(RequestSpecification vJsonRequest, String vResource) throws Exception {
		prop = projectconfig.readConfig("Config");
		response = vJsonRequest.get(prop.getProperty("baseUrl") + vResource);
		return response;
	}
	
	public Response executePatchRequest(RequestSpecification vJsonRequest, String vResource) throws Exception {
		prop = projectconfig.readConfig("Config");
		response = vJsonRequest.patch(prop.getProperty("baseUrl") + vResource);
		return response;
	}
	
	public Response executeDeleteRequest(RequestSpecification vJsonRequest, String vResource) throws Exception {
		prop = projectconfig.readConfig("Config");
		response = vJsonRequest.delete(prop.getProperty("baseUrl") + vResource);
		return response;
	}
	
	public void validateResponse(Response vJsonResponse, String jsonPath, int expStatusCode) {
		ValidatableResponse vr = vJsonResponse.then();
		vr.assertThat().body(jsonPath, Matchers.equalTo(expStatusCode));
	}
	
	public void validateResponse(Response vJsonResponse, String jsonPath, String expStatusCode) {
		ValidatableResponse vr = vJsonResponse.then();
		vr.assertThat().body(jsonPath, Matchers.equalTo(expStatusCode));
	}

}
