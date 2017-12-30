package com.imademethink_sikulix_automation;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features 			= {"src/test/resources"},
		//tags 			    = {},
//		dryRun 			= false,
//		strict 			    = false,
//		monochrome = true,
//		format 			= {
//   									"pretty",
//   									"html:target/cucumber",
//   									"json:target/cucumber/cucumber.json"
//   									},
		glue 			    = {"com.imademethink_sikulix_automation"}
)

public class SikuliRunnerTest {

}
