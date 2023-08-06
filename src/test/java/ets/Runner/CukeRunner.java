package ets.Runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "./src/test/java/ets/Features",
		glue = "ets/StepDefinitions",
		plugin = { "pretty", "json:src/test/java/ets/Reports/report.json", 
				"junit:src/test/java/ets/Reports/report.xml", 
				"html:src/test/java/ets/Reports/report.html",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" 
				},
		monochrome=true
		)

public class CukeRunner {

}
