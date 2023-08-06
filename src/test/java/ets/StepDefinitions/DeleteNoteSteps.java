package ets.StepDefinitions;

import java.util.List;
import java.util.Map;

import ets.BaseLibrary.Utils;
import ets.Resources.ProjectConfig;
import ets.RestAPIBase.RestHttpMethods;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DeleteNoteSteps {
	private String noteId, jsonStringPayload;
	private Utils utils = new Utils();
	private RestHttpMethods restmethods = new RestHttpMethods();
	private RequestSpecification vJsonRequest;
	private ProjectConfig projectconfig = new ProjectConfig();
	private Response vJsonResponse;
	
	@When("user wants to delete a note {string}")
	public void user_wants_to_delete_a_note(String vResource, DataTable dataTable) throws Exception {
		List<Map<String, String>> dtMap = dataTable.asMaps(String.class, String.class);
		noteId = dtMap.get(0).get("noteid");
		
		vJsonRequest = restmethods.createDeleteRequest();
		vJsonResponse = restmethods.executeDeleteRequest(vJsonRequest, vResource+"/"+noteId);
	}

	@Then("validate delete note status code {int}")
	public void validate_delete_note_status_code(Integer expStatusCode) {
		restmethods.validateResponse(vJsonResponse, "status", expStatusCode);
	}

	@Then("validate delete note success message {string}")
	public void validate_delete_note_success_message(String expMessage) {
		restmethods.validateResponse(vJsonResponse, "message", expMessage);
	}

}
