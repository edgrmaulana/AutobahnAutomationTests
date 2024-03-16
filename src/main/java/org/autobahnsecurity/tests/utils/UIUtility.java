package org.autobahnsecurity.tests.utils;

import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import java.util.Set;

public class UIUtility extends CommonUtility{
    protected void clickByWebElementFacade(WebElementFacade webElementFacade){
        webElementFacade.click();
    }

    protected Boolean isElementVisibleByWebElementFacade(WebElementFacade webElementFacade){
        return webElementFacade.isVisible();
    }


    protected void inputText(String text, WebElementFacade webElementFacade){
        webElementFacade.clear();
        webElementFacade.sendKeys(text);
    }

    protected void switchToEmailIFrame(){
        getDriver().switchTo().frame(
                getDriver().findElement(By.xpath("//iframe[contains(@srcdoc, 'autobahn')]")));
    }

    protected void switchToAdsIFrame(){
        getDriver().switchTo().frame(
                getDriver().findElement(By.xpath("//iframe[contains(@id, 'google_ads_iframe')]")));
    }

    protected void changeTabs(String value){
        Set<String> windowIds = getDriver().getWindowHandles();

        for (String windowId : windowIds ){
            getDriver().switchTo().window(windowId);
            if (getDriver().getTitle().contains(value)){
                break;
            }
        }
    }

    protected void closeTab(){
        getDriver().close();
    }

    protected void scrollPage(WebElementFacade webElementFacade){
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", webElementFacade);
    }

    protected String getTextByWebElementFacade(WebElementFacade webElementFacade){
        return webElementFacade.getText();
    }
}
