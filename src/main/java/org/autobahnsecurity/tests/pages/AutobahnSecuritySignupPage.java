package org.autobahnsecurity.tests.pages;

import net.serenitybdd.core.pages.WebElementFacade;
import org.autobahnsecurity.tests.utils.UIUtility;
import org.openqa.selenium.support.FindBy;

public class AutobahnSecuritySignupPage extends UIUtility {

    @FindBy(css = "input[placeholder='Work email*']")
    WebElementFacade userEmailLogin;

    @FindBy(css = "input[placeholder='Password*']")
    WebElementFacade userPasswordLogin;

    @FindBy(css = ".button-text.paragraph.button-large")
    WebElementFacade signUpButton;

    @FindBy(css = "input[placeholder='First name*']")
    WebElementFacade firstNameInput;

    @FindBy(css = "input[placeholder='Last name*']")
    WebElementFacade lastNameInput;

    @FindBy(css = ".placeholderActive.option-selected")
    WebElementFacade dropdownIndustry;

    @FindBy(css = ".iti__arrow")
    WebElementFacade dropdownPhoneCountry;

    @FindBy(css = "input[placeholder='Phone number*']")
    WebElementFacade phoneNumberInput;

    @FindBy(css = "div[class='scan-info'] div[class='header']")
    WebElementFacade homepageHeader;

    @FindBy(css = "div[id='create-new-account-btn'] p[class='button-text paragraph button-large']")
    WebElementFacade createNewAccountButton;

    @FindBy(css = "label[class='label']")
    WebElementFacade errorNotificationWarning;

    @FindBy(css = ".bar-text")
    WebElementFacade errorFeedbackWeakPassword;



    //    --------------- Click Action

    public void clickSignUpButton(){
        clickByWebElementFacade(signUpButton);
    }

    public void clickIndustryDropdown(){
        clickByWebElementFacade(dropdownIndustry);
    }
    public void clickPhoneNumberDropdown(){
        clickByWebElementFacade(dropdownPhoneCountry);
    }

    public void clickCreateNewAccountButton(){
        clickByWebElementFacade(createNewAccountButton);
    }

    public void filterPhoneNumberCountry(String value){
        String path = String.format("//span[contains(text(),'%s')]", value);
        WebElementFacade xPath = findBy(path);
        scrollPage(xPath);
        if (xPath.getText().equalsIgnoreCase(value)){
            clickByWebElementFacade(xPath);
        }
    }

    public void filterIndustryName(String value){
        for (int i = 0; i < 18; i++){
            String css = "#item-" + i;
            WebElementFacade cssPath = findBy(css);
            scrollPage(cssPath);
            if (cssPath.getText().equalsIgnoreCase(value)){
                clickByWebElementFacade(cssPath);
            }
        }
    }

    //    --------------- Input Text Action

    public void inputTextToUsername(String value){
        inputText(value, userEmailLogin);
    }

    public void inputTextToPassword(String value){
        inputText(value, userPasswordLogin);
    }

    public void inputTextToFirstName(String value){
        inputText(value, firstNameInput);
    }

    public void inputTextToLastName(String value){
        inputText(value, lastNameInput);
    }

    public void inputTextToPhoneNumber(String value){
        inputText(value, phoneNumberInput);
    }

    //    --------------- Get Text Action

    public String getHomepageHeaderText(){
        return getTextByWebElementFacade(homepageHeader);
    }

    public String getErrorNotificationOnSignup(){
        return getTextByWebElementFacade(errorNotificationWarning);
    }

    public String getErrorNotificationWeakPassword(){
        return getTextByWebElementFacade(errorFeedbackWeakPassword);
    }

    //    --------------- Windows Action

    public void changeWindowToSelected(String value){
        changeTabs(value);
    }

    //    --------------- Verify Action

    public void closeCurrentTabs(){
        closeTab();
    }

    public String getRedirectionUrl(){
        return getUrl();
    }

    public Boolean isHomepageHeaderVisible(){
        return isElementVisibleByWebElementFacade(homepageHeader);
    }

    public Boolean isJoinAccountElementVisible(){
        return isElementVisibleByWebElementFacade(createNewAccountButton);
    }

    public Boolean isWeakPasswordNotificationVisible(){
        return isElementVisibleByWebElementFacade(errorFeedbackWeakPassword);
    }
}
