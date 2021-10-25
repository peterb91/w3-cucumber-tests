package w3.org.stepdefs;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.steps.UIInteractionSteps;
import net.serenitybdd.rest.SerenityRest;
import w3.org.W3OrgEndPoints;


public class ApiSteps extends UIInteractionSteps {

    @When("I send GET request to {string} endpoint")
    public void iSendGETRequestToEndpoint(String endpointName) {
        SerenityRest.when().get(W3OrgEndPoints.valueOf(endpointName).getUrl());
    }

    @Then("the API response code is {int}")
    public void theAPIResponseCodeIs(int responseCode) {
        SerenityRest.restAssuredThat(response -> response.statusCode(responseCode));
    }
}
