package org.autobahnsecurity.tests.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.autobahnsecurity.tests.data.AutobahnSecuritySignupData;
import org.autobahnsecurity.tests.pages.AutobahnSecuritySignupPage;
import org.autobahnsecurity.tests.pages.TemporaryEmailPage;
import org.autobahnsecurity.tests.properties.AutobahnSecurityProperties;
import org.autobahnsecurity.tests.utils.CommonUtility;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class AutobahnSignupSteps {

    CommonUtility commonUtility = new CommonUtility();

    AutobahnSecurityProperties autobahnProperties = new AutobahnSecurityProperties();

    AutobahnSecuritySignupData autobahnData = new AutobahnSecuritySignupData();

    TemporaryEmailPage temporaryEmailPage = new TemporaryEmailPage();

    AutobahnSecuritySignupPage autobahnPage = new AutobahnSecuritySignupPage();

    @Given("User tried to open Autobahn Signup Page")
    public void userTriedToOpenAutobahnSignupPage() throws IOException {
        commonUtility.newTab();
        commonUtility.openPage(
                autobahnProperties.getProperties("defaultUrl"));
    }

    @When("User get email from temporary email provider in {string}")
    public void userGetEmailFromTemporaryEmailProviderInDefaultEmailPage(String value) throws IOException {
        value = value.equalsIgnoreCase("default") ? autobahnProperties.getProperties("defaultEmailUrl") : value;
        commonUtility.openPage(value);
    }

    @Then("User copying email from temporary email page")
    public void userCopyingEmailFromTemporaryEmailPage() throws IOException, UnsupportedFlavorException, InterruptedException {
        Thread.sleep(5000);
        temporaryEmailPage.clickOnUserEmail();
        autobahnData.setUserEmail(
                Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor).toString());
        System.out.println(autobahnData.getUserEmail());
    }

    @And("User paste the data from clipboard as email")
    public void userPasteTheDataFromClipboardAsEmail(){

    }

    @And("User tried to input user email as {string}")
    public void userTriedToInputUserEmailAsEmail(String value) {
        value = value.equalsIgnoreCase("default") ? autobahnData.getUserEmail() : value;
        autobahnPage.inputTextToUsername(
                value);
    }

    @And("User tried to input user password as {string}")
    public void userTriedToInputUserPasswordAsPassword(String value) throws IOException {
        value = value.equalsIgnoreCase("default") ? autobahnProperties.getProperties("password") : value;
        autobahnPage.inputTextToPassword(value);
        autobahnData.setUserPassword(value);
    }

    @And("User click on sign up button")
    public void userClickOnSignUpButton() {
        autobahnPage.clickSignUpButton();
    }

    @Then("User wait for {int} seconds")
    public void userWaitForSeconds(int value) throws InterruptedException {
        Thread.sleep(value * 1000L);
    }

    @Then("User tried to input first name as {string}")
    public void userTriedToInputFirstNameAsFirstName(String value) {

    }

    @Then("User tried to input last name as {string}")
    public void userTriedToInputLastNameAsLastName(String value) {
    }
}
