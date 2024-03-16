package org.autobahnsecurity.tests.steps;

import com.github.javafaker.Faker;
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

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

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
        Thread.sleep(10000);
        temporaryEmailPage.clickOnUserEmail();
        autobahnData.setUserEmail(
                Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor).toString());
        System.out.println(autobahnData.getUserEmail());
    }

    @And("User tried to input user email as {string}")
    public void userTriedToInputUserEmailAsEmail(String value) throws IOException {
        value = value.equalsIgnoreCase("default") ? autobahnData.getUserEmail() : value;
        value = value.equalsIgnoreCase("existing") ? autobahnProperties.getProperties("existingEmail") : value;
        autobahnPage.inputTextToUsername(value);
    }

    @And("User tried to input user password as {string}")
    public void userTriedToInputUserPasswordAsPassword(String value) throws IOException {
        value = value.equalsIgnoreCase("default") ? autobahnProperties.getProperties("password") : value;
        autobahnPage.inputTextToPassword(value);
        autobahnData.setUserPassword(value);
    }

    @And("User click on sign up button")
    public void userClickOnSignUpButton() {
        if (autobahnData.getUserPassword().equalsIgnoreCase("TestingQwerty123!")){
            autobahnPage.clickSignUpButton();
        }
    }

    @Then("User wait for {int} seconds")
    public void userWaitForSeconds(int value) throws InterruptedException {
        Thread.sleep(value * 1000L);
    }

    @Then("User tried to input first name as {string}")
    public void userTriedToInputFirstNameAsFirstName(String value) {
        Faker faker = new Faker();

        value = value.equalsIgnoreCase("random") ? faker.name().firstName() : value;

        autobahnPage.inputTextToFirstName(value);
    }

    @Then("User tried to input last name as {string}")
    public void userTriedToInputLastNameAsLastName(String value) {
        Faker faker = new Faker();

        value = value.equalsIgnoreCase("random") ? faker.name().lastName() : value;

        autobahnPage.inputTextToLastName(value);
    }

    @And("User tried to to choose industry as {string}")
    public void userTriedToToChooseIndustryAsIndustry(String value) {
        autobahnPage.clickIndustryDropdown();
        autobahnPage.filterIndustryName(value);
    }

    @And("User tried to choose phone number country to {string}")
    public void userTriedToChoosePhoneNumberCountryToCountry(String value) {
        autobahnPage.clickPhoneNumberDropdown();
        autobahnPage.filterPhoneNumberCountry(value);
    }

    @And("User tried to input phone number as {string}")
    public void userTriedToInputPhoneNumberAsPhoneNumber(String value) throws IOException {
        value = value.equalsIgnoreCase("default") ? autobahnProperties.getProperties("defaultPhoneNumber") : value;
        value = value.equalsIgnoreCase("random") ? ("8" + System.currentTimeMillis()).substring(0, 12) : value;
        System.out.println(value);
        autobahnPage.inputTextToPhoneNumber(value);
    }

    @And("User click on start using autobahn button")
    public void userClickOnStartUsingAutobahnButton() throws InterruptedException {
        autobahnPage.clickSignUpButton();
        if (autobahnPage.isJoinAccountElementVisible().equals(true)){
            autobahnPage.clickCreateNewAccountButton();
        }
        Thread.sleep(2000);
        autobahnPage.closeCurrentTabs();
    }

    @Then("User tried change tabs to {string} page")
    public void userTriedChangeTabsToEmailPage(String value){
        value = value.equalsIgnoreCase("email") ? "Temp Mail" : value;
        value = value.equalsIgnoreCase("Autobahn") ? "Autobahn Security" : value;
        autobahnPage.changeWindowToSelected(value);
    }

    @Then("User refresh the page and click on received verification email")
    public void userRefreshThePageAndClickOnReceivedVerificationEmail() {
        temporaryEmailPage.filterAndClickSignupEmail("signup@autobahn-security.com");
    }

    @And("User click on verify account button")
    public void userClickOnVerifyAccountButton(){
        temporaryEmailPage.clickOnVerifyUserEmail();
    }

    @Then("User should be redirected to {string} page")
    public void userShouldBeRedirectedToPage(String value) {
        value = value.equalsIgnoreCase("Autobahn home") ? "https://autobahn.security/report?scan_id" : value;
        assertThat("Redirection URL is Incorrect", autobahnPage.getRedirectionUrl(),
                containsString(value));
    }

    @And("User should be successfully registered")
    public void userShouldBeSuccessfullyRegistered(){
        assertThat("Homepage is not visible", autobahnPage.isHomepageHeaderVisible(),
                equalTo(true));
        assertThat("User is not registered yet", autobahnPage.getHomepageHeaderText(),
                containsString("Autobahn Demo - External"));

        System.out.println(autobahnPage.getHomepageHeaderText());
    }

    @Then("Close ads popup if ads is showing up")
    public void closeAdsPopupIfAdsIsShowingUp() throws InterruptedException {
        Thread.sleep(1000);
        if (temporaryEmailPage.getUrl().contains("#google_vignette")){
            temporaryEmailPage.clickOnCloseAds();
        }
    }

    @Then("User error notification should be showing up as {string}")
    public void userErrorNotificationShouldBeShowingUpAsErrorNotification(String value) {
        if (autobahnPage.isWeakPasswordNotificationVisible().equals(true) && value.contains("Weak")){
            assertThat("Incorrect error message is showing up", autobahnPage.getErrorNotificationWeakPassword(),
                    containsString(value));
        } else {
            assertThat("Incorrect error message is showing up", autobahnPage.getErrorNotificationOnSignup(),
                    containsString(value));
        }

    }
}
