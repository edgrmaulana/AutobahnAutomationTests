package org.autobahnsecurity.tests.pages;

import net.serenitybdd.core.pages.WebElementFacade;
import org.autobahnsecurity.tests.utils.UIUtility;
import org.openqa.selenium.support.FindBy;

public class TemporaryEmailPage extends UIUtility {

    @FindBy(xpath = "//input[@id='Dont_use_WEB_use_API']")
    WebElementFacade userEmailText;

    //    --------------- Click Action
    public void clickOnUserEmail(){
        clickByWebElementFacade(userEmailText);
    }

    public String getUserEmailText(){
        return getTextByWebElementFacade(userEmailText);
    }
}
