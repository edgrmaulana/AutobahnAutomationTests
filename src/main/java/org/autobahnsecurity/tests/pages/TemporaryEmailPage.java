package org.autobahnsecurity.tests.pages;

import net.serenitybdd.core.pages.WebElementFacade;
import org.autobahnsecurity.tests.utils.UIUtility;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.FindBy;

public class TemporaryEmailPage extends UIUtility {

    @FindBy(xpath = "//input[@id='Dont_use_WEB_use_API']")
    WebElementFacade userEmailText;

    @FindBy(xpath = "//a[@href='/en/' and contains(text(), 'Refresh')]")
    WebElementFacade refreshButton;

    @FindBy(xpath = "//a[normalize-space()='Verify Account']")
    WebElementFacade verifyAccountButton;

    @FindBy(xpath = "//span[@dir='auto' and contains(text(),'Close')]")
    WebElementFacade closeAdsButton;



    //    --------------- Click Action
    public void clickOnUserEmail(){
        clickByWebElementFacade(userEmailText);
    }

    public void filterAndClickSignupEmail(String value){

        String path = String.format("//span[normalize-space()='%s']", value);
        WebElementFacade xPath = findBy(path);

        do {
            try{
                clickByWebElementFacade(refreshButton);
                System.out.println("Button status is = " + xPath.isClickable());
            } catch (NoSuchElementException error){
                clickByWebElementFacade(refreshButton);
                System.out.println("Refreshing Until Verification Email is Showing Up");
            }
        } while (!xPath.isClickable());

        scrollPage(xPath);
        if (xPath.getText().contains(value)){
            clickByWebElementFacade(xPath);
        }
    }

    public void clickOnVerifyUserEmail(){
        switchToEmailIFrame();
        clickByWebElementFacade(verifyAccountButton);
    }

    public void clickOnCloseAds(){
        switchToAdsIFrame();
        clickByWebElementFacade(closeAdsButton);
    }
}
