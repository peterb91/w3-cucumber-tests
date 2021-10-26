package w3.org.stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.steps.UIInteractionSteps;
import net.serenitybdd.rest.SerenityRest;
import org.junit.Assert;
import w3.org.W3OrgEndPoints;

import static w3.org.config.TestConfig.W3ORG_BASE_URI;


public class ApiSteps extends UIInteractionSteps {

    @Given("the w3 org BASE.URI is set")
    public void theW3BaseUriIsSet() {
        Assert.assertFalse("W3 org Base URI is not defined", W3ORG_BASE_URI.isEmpty());
    }

    @When("I send GET request to {string} endpoint")
    public void iSendGETRequestToEndpoint(String endpointName) {
        SerenityRest.when().get(W3OrgEndPoints.valueOf(endpointName).getUrl());
    }

    @Then("the API response code is {int}")
    public void theAPIResponseCodeIs(int responseCode) {
        SerenityRest.restAssuredThat(response -> response.statusCode(responseCode));
    }
}
