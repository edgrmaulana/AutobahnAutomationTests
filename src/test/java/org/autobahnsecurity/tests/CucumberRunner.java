package org.autobahnsecurity.tests;

import cucumber.runtime.SerenityObjectFactory;
import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/resources/features",
        glue = {"org.autobahnsecurity.tests.steps",
                "org.autobahnsecurity.tests.hooks"},
        objectFactory = SerenityObjectFactory.class,
        stepNotifications = true,
        tags = "@AutobahnSignupPageFeature")
public class CucumberRunner {

}
