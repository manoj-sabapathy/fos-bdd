package com.matrixmedicalnetwork.bdd.stepdefinitions;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import com.matrixmedicalnetwork.bdd.base.BaseTest;
import com.matrixmedicalnetwork.bdd.utils.ErrorResponse;
import com.matrixmedicalnetwork.bdd.utils.SharedRequest;
import com.matrixmedicalnetwork.bdd.utils.SharedResponse;
import com.matrixmedicalnetwork.bdd.utils.configLoader;

import io.cucumber.java8.En;
import io.restassured.response.Response;

public class OAuthSteps extends BaseTest implements En {

    private Response response;

    public OAuthSteps(SharedRequest sharedRequest, SharedResponse sharedResponse) {

        Given("the client sends OAuth token using clientId as {string}, clientSecret as {string}, grantType as {string}, scope as {string}",
                (String clientId, String clientSecret, String grantType, String scope) -> {

                    response = given().auth().preemptive().basic(clientId, clientSecret)
                            .contentType("application/x-www-form-urlencoded").formParam("grant_type", grantType)
                            .formParam("scope", scope).when().post(configLoader.getProperty("token_url"));

                    sharedResponse.setResponse(response);
                });

        Then("the API should return with error {string} and errorcode {int}", (String error, Integer errorCode) -> {

            ErrorResponse errorResponse = sharedResponse.getResponse().as(ErrorResponse.class);
            assertThat(errorResponse.getError()).isEqualTo(error);

            assertThat(errorResponse.getErrorCodes()).hasSize(1).contains(errorCode);

        });

    }
}
