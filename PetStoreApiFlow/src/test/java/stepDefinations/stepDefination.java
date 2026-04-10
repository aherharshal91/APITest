package stepDefinations;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.junit.Assert.*;
public class stepDefination {
    @Given("^All Pet status data provided$")
    public void allPetStatusDataProvided() {
    }

    @When("^User call Get request for \"([^\"]*)\"$")
    public void userCallGetRequestFor(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^\"([^\"]*)\" shown with status  \"([^\"]*)\"$")
    public void shownWithStatus(String arg0, String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("^status body is Ok$")
    public void statusBodyIsOk() {
    }
}
