package w3.org.stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.steps.UIInteractionSteps;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import w3.org.pages.CommonPage;

import java.util.ArrayList;
import java.util.List;

import static w3.org.config.Constants.*;

public class CommonSteps extends UIInteractionSteps {

    @Steps
    CommonPage commonPage;

    @Given("w3 page {string} is loaded")
    @When("I load w3 page {string}")
    public void w3PageIsLoaded(String pageName) {
        commonPage.openGivenPage(pageName);
        commonPage.waitForPageLoad(TIMEOUT_FOR_PAGE_LOAD);
    }

    @Then("there are no console errors visible.")
    public void thereAreNoConsoleErrorsVisible() {
        List<LogEntry> logEntries = getDriver().manage().logs().get(LogType.BROWSER).toJson();
        Assert.assertTrue(String.format("There are console errors in the console: \"%s\"", logEntries),
                logEntries.isEmpty());
    }

    @When("I collect all links urls")
    public void iCollectAllLinksUrls() {
        Serenity.setSessionVariable(PAGE_LINKS_URLS_LIST).to(commonPage.getLinksUrls());
    }

    @When("I check every link url")
    public void iCheckEveryLinkUrl() {
        List<String> linksUrlsList = Serenity.sessionVariableCalled(PAGE_LINKS_URLS_LIST);
        List<List<String>> linksResponsesList = new ArrayList<>();
        for(String url : linksUrlsList) {
            List<String> linkStatusPairsList = new ArrayList<>();
            linkStatusPairsList.add(url);
            linkStatusPairsList.add(String.valueOf(SerenityRest.when().get(url).statusCode()));
            linksResponsesList.add(linkStatusPairsList);
        }
        Serenity.setSessionVariable(GET_PAGE_LINKS_RESPONSE_LIST).to(linksResponsesList);
    }

    @Then("every link is valid")
    public void everyLinkIsValid() {
        List<List<String>> linksAndStatuses = Serenity.sessionVariableCalled(GET_PAGE_LINKS_RESPONSE_LIST);
        for(List<String> linkStatus : linksAndStatuses) {
            SoftAssertions.assertSoftly(softAssert -> softAssert.assertThat(linkStatus.get(1))
                    .as(String.format("Link: %s is not leading to live page. The page response code is: %s",
                            linkStatus.get(0), linkStatus.get(1)))
                    .matches("[1-3].."));
        }
    }
}
