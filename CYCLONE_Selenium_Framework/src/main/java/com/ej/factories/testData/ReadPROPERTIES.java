package com.ej.factories.testData;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
@SuppressWarnings("unused")
public class ReadPROPERTIES {

    private final String propertiesFilePath;

    public ReadPROPERTIES(String propertiesFilePath) {
        this.propertiesFilePath = propertiesFilePath;
    }
    public String readProperties(String key){
        InputStream input = ReadPROPERTIES.class.getClassLoader().getResourceAsStream(propertiesFilePath);

        Properties properties = new Properties();

        try {
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("PropertiesFile Not Found");
        }

        if (properties.get(key) == null) {
            throw new NullPointerException("Invalid Property Key!");
        }

        return properties.getProperty(key);
    }
}
