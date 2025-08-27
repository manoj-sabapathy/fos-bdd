package com.matrixmedicalnetwork.bdd.utils;

import io.restassured.specification.RequestSpecification;

public class SharedRequest {

    private static RequestSpecification requestSpec;

    public static RequestSpecification getRequestSpec() {
        return requestSpec;
    }

    public static void setRequestSpec(RequestSpecification reqSpec) {
        requestSpec = reqSpec;
    }
}
