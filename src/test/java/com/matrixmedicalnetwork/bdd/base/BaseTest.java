package com.matrixmedicalnetwork.bdd.base;

import org.junit.BeforeClass;

import com.matrixmedicalnetwork.bdd.utils.configLoader;

import io.restassured.RestAssured;

public class BaseTest {

    protected static String baseUrl;
    protected static String dbUrl;
    protected static String dbUser;
    protected static String dbPassword;

    static {
        configLoader.loadProperties();
        baseUrl = configLoader.getProperty("base.url");
        dbUrl = configLoader.getProperty("db.url");
        dbUser = configLoader.getProperty("db.username");
        dbPassword = configLoader.getProperty("db.password");

        RestAssured.baseURI = baseUrl;
    }

    @BeforeClass
    public static void setUpRestAssured() {
        if (baseUrl == null || baseUrl.isEmpty()) {
            throw new RuntimeException("Base Url not set! Check properties file and keys.");
        }
        RestAssured.baseURI = baseUrl;
    }
}
