package org.autobahnsecurity.tests.properties;


import com.github.javafaker.Faker;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class AutobahnSecurityProperties {
    public String getProperties(String value) throws IOException {
        FileReader pathprop = new FileReader("src/test/resources/application.properties");
        Properties prop = new Properties();
        prop.load(pathprop);
        String properties = String.format("autobahn.%s", value);
        return prop.getProperty(properties);
    }
}
