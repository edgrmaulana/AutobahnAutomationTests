package org.autobahnsecurity.tests.utils;

import net.serenitybdd.core.steps.UIInteractions;
import org.openqa.selenium.WindowType;

public class CommonUtility extends UIInteractions {
    public void openPage(String url){
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        getDriver().manage().window().maximize();
        openUrl(url);
    }

    public void newTab(){
        getDriver().switchTo().newWindow(WindowType.TAB);
    }
}
