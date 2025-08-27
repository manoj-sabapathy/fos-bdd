package com.matrixmedicalnetwork.bdd.stepdefinitions;

import com.matrixmedicalnetwork.bdd.base.BaseTest;
import com.matrixmedicalnetwork.bdd.utils.DBQueries;
import com.matrixmedicalnetwork.bdd.utils.DbUtils;
import com.matrixmedicalnetwork.bdd.utils.SharedRequest;
import com.matrixmedicalnetwork.bdd.utils.SharedResponse;
import io.cucumber.java8.En;
import io.restassured.response.Response;

import static org.assertj.core.api.Assertions.assertThat;

public class WorkOrderSteps extends BaseTest implements En {

    private Response response;

    public WorkOrderSteps(SharedRequest sharedRequest, SharedResponse sharedResponse) {

        Then("the work-order API and db total count should match", () -> {

            String dbResponse = String.valueOf(DbUtils.getWorkOrderByQuery(DBQueries.getWorkOrderCount()));

            assertThat(dbResponse).isEqualTo(sharedResponse.getResponse().jsonPath().getString("totalCount"));

        });
    }
}