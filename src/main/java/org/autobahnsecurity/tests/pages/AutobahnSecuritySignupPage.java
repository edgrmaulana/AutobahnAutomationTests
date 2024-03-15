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

    //    --------------- Click Action

    public void clickSignUpButton(){
        clickByWebElementFacade(signUpButton);
    }

    //    --------------- Text Action

    public void inputTextToUsername(String value){
        inputText(value, userEmailLogin);
    }

    public void inputTextToPassword(String value){
        inputText(value, userPasswordLogin);
    }
}
