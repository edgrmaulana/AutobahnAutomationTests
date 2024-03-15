package org.autobahnsecurity.tests.utils;

import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;

public class UIUtility extends CommonUtility{
    protected void clickByWebElementFacade(WebElementFacade webElementFacade){
        webElementFacade.click();
    }

    protected Boolean isElementVisibleByWebElementFacade(WebElementFacade webElementFacade){
        return webElementFacade.isVisible();
    }

    protected Boolean isElementDisabledByWebElementFacade(WebElementFacade webElementFacade){
        return webElementFacade.getAttribute("class").contains("disabled");
    }

    protected void switchToIFrame(){
        getDriver().switchTo().frame(
                getDriver().findElement(By.xpath("//iframe[contains(@src, 'sandbox.midtrans')]")));
    }

    protected void inputText(String text, WebElementFacade webElementFacade){
        webElementFacade.clear();
        webElementFacade.sendKeys(text);
    }

    protected void scrollPage(WebElementFacade webElementFacade){
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", webElementFacade);
    }

    protected String getTextByWebElementFacade(WebElementFacade webElementFacade){
        return webElementFacade.getText();
    }

    protected void deleteText(WebElementFacade webElementFacade){
        webElementFacade.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
    }

    protected void pasteText(WebElementFacade webElementFacade){
        webElementFacade.sendKeys(Keys.chord(Keys.CONTROL,"v"));
    }
}
