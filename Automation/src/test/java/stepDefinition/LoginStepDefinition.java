package stepDefinition;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.And;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
public class LoginStepDefinition {

    @Given("^user is on net bancking landing page$")
    public void user_is_on_net_bancking_landing_page() throws Throwable {
       System.out.println("Given method");
    }

    @When("^user login into the application with username \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void user_login_into_the_application_with_username_and_password(String username, String password) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        System.out.println(username+","+password);
    }

    @Then("^Home page is populated$")
    public void home_page_is_populated() throws Throwable {
    	System.out.println("then method");
    }

    @And("^Cards are displayed \"([^\"]*)\"$")
    public void cards_are_displayed(String flag) throws Throwable {
    	System.out.println("and method"+flag);
    }

}