package ets.BaseLibrary;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import org.json.JSONObject;

import ets.Resources.ProjectConfig;
import ets.RestAPIBase.RestHttpMethods;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	private RequestSpecification vJsonRequest;
	private Response vJsonResponse;
	private RestHttpMethods restmethods = new RestHttpMethods();
	private ProjectConfig projectconfig = new ProjectConfig();
	private String jsonStringPayload;
	
	public void generateToken() throws Exception {
		jsonStringPayload = setupTokenPayload();
		vJsonRequest = restmethods.createPostTokenRequest(jsonStringPayload);
		vJsonResponse = restmethods.executePostRequest(vJsonRequest, projectconfig.readConfig("Config").getProperty("tokenResource"));
		restmethods.validateResponse(vJsonResponse, "status", 200);
		String token = vJsonResponse.jsonPath().get("data.token");
		System.out.println("Response: "+token);
		projectconfig.writeConfig("token", token);
	}
	
	public String setupTokenPayload() throws Exception {
		String file = "src/test/java/ets/Resources/token.json";
		String jString = readJsonAsString(file);
		jString = jString.replace("{{useremail}}", projectconfig.readConfig("Config").getProperty("userEmail"));
		jString = jString.replace("{{userpassword}}", projectconfig.readConfig("Config").getProperty("userPassword"));
		return jString;
	}
	
	public String setupCreateNotePayload(String vTitle, String vDescription, String vCategory) throws Exception {
		String file = "src/test/java/ets/Resources/createnote.json";
		String jString = readJsonAsString(file);
		jString = jString.replace("{{title}}", vTitle);
		jString = jString.replace("{{description}}", vDescription);
		jString = jString.replace("{{category}}", vCategory);
		return jString;
	}
	
	public String setupUpdateNotePayload(String vNoteId) throws Exception {
		String file = "src/test/java/ets/Resources/updatenote.json";
		String jString = readJsonAsString(file);
		jString = jString.replace("{{noteId}}", vNoteId);
		return jString;
	}
	
	public String readJsonAsString(String file) throws Exception {
		return new String(Files.readAllBytes(Paths.get(file)));
	}
	
	public JSONObject readStringAsJson(String jsonString) throws IOException {
		return new JSONObject(jsonString);
	}
}
