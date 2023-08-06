package ets.StepDefinitions;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import ets.BaseLibrary.Utils;
import ets.Resources.ProjectConfig;
import ets.RestAPIBase.RestHttpMethods;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CreateNoteSteps {
	private Utils utils = new Utils();
	private RestHttpMethods restmethods = new RestHttpMethods();
	private RequestSpecification vJsonRequest;
	private ProjectConfig projectconfig = new ProjectConfig();
	private Response vJsonResponse;
	private String createNoteResource, title, description, category, jsonStringPayload;
	
	@Given("user have access of application")
	public void user_have_access_to() throws Exception {
		utils.generateToken();
	}

	@When("user wants to create a note {string}")
	public void user_wants_to_create_a_note(String expResource, DataTable dataTable) throws Exception {
		List<Map<String, String>> dtMap = dataTable.asMaps(String.class, String.class);
		title = dtMap.get(0).get("title"); 
		description = dtMap.get(0).get("description"); 
		category = dtMap.get(0).get("category");
		jsonStringPayload = utils.setupCreateNotePayload(title, description, category);
		vJsonRequest = restmethods.createPostRequest(jsonStringPayload);
		vJsonResponse = restmethods.executePostRequest(vJsonRequest, expResource);
	}

	@Then("validate create note status code {int}")
	public void validate_status_code(int expStatusCode) {
		restmethods.validateResponse(vJsonResponse, "status", expStatusCode);
	}

	@Then("validate create note success message {string}")
	public void validate_success_message(String expMessage) {
		restmethods.validateResponse(vJsonResponse, "message", expMessage);
	}
}
