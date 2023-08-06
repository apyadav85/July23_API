package ets.StepDefinitions;

import ets.BaseLibrary.Utils;
import ets.Resources.ProjectConfig;
import ets.RestAPIBase.RestHttpMethods;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetNoteSteps {
	private String noteId, jsonStringPayload;
	private Utils utils = new Utils();
	private RestHttpMethods restmethods = new RestHttpMethods();
	private RequestSpecification vJsonRequest;
	private ProjectConfig projectconfig = new ProjectConfig();
	private Response vJsonResponse;
	
	@When("user wants to get all notes {string}")
	public void user_wants_to_get_all_notes(String vResource) throws Exception {
		vJsonRequest = restmethods.createGetRequest();
		vJsonResponse = restmethods.executeGetRequest(vJsonRequest, vResource);
		System.out.println("Data: "+vJsonResponse.asPrettyString());
	}

	@Then("validate get note status code {int}")
	public void validate_get_note_status_code(Integer expStatusCode) {
		restmethods.validateResponse(vJsonResponse, "status", expStatusCode);
	}

	@Then("validate get note success message {string}")
	public void validate_get_note_success_message(String expMessage) {
		restmethods.validateResponse(vJsonResponse, "message", expMessage);
	}
}
