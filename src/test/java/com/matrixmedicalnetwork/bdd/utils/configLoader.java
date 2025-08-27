package com.matrixmedicalnetwork.bdd.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class configLoader {

    private static Properties properties = new Properties();

    public static void loadProperties() {
        String env = System.getProperty("env", "qa");

        String propFileName = "environments/" + env + ".properties";

        try (InputStream input = configLoader.class.getClassLoader().getResourceAsStream(propFileName)) {
            if (input == null) {
                throw new RuntimeException("properties file not found:" + propFileName);
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load properties file: " + propFileName, e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

}
