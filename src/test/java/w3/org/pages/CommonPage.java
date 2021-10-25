package w3.org.pages;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import w3.org.W3OrgEndPoints;

import java.util.List;
import java.util.stream.Collectors;

public class CommonPage extends PageObject {

    private final By linkTag = By.tagName("a");

    public void openGivenPage(String pageName) {
        super.openUrl(getPageUrl(pageName));
    }

    private String getPageUrl(String pageName) {
        try {
            return W3OrgEndPoints.valueOf(pageName).getUrl();
        } catch (Exception e) {
            throw new RuntimeException("Invalid page name");
        }
    }

    public void waitForPageLoad( int timeOutInSeconds) {
        new WebDriverWait(getDriver(), timeOutInSeconds)
                .until(webDriver -> ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState")
                        .equals("complete"));
    }

    public List<String> getLinksUrls() {
        return findAll(linkTag).stream()
                .filter(element -> !element.hasClass("current"))
                .map(element -> element.getAttribute("href"))
                .map(String::trim)
                .filter(hyperlink -> !hyperlink.startsWith("mailto"))
                .collect(Collectors.toList());
    }
}
