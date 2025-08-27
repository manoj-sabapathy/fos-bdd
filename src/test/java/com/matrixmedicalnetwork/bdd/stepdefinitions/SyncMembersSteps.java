package com.matrixmedicalnetwork.bdd.stepdefinitions;

import com.matrixmedicalnetwork.bdd.base.BaseTest;
import com.matrixmedicalnetwork.bdd.dto.MemberAppointmentDTO;
import com.matrixmedicalnetwork.bdd.utils.DBQueries;
import com.matrixmedicalnetwork.bdd.utils.DbUtils;
import com.matrixmedicalnetwork.bdd.utils.SharedRequest;
import com.matrixmedicalnetwork.bdd.utils.SharedResponse;
import io.cucumber.java8.En;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class SyncMembersSteps extends BaseTest implements En {

    private Response response;

    public SyncMembersSteps(SharedRequest sharedRequest, SharedResponse sharedResponse) {

        When("the client sends a POST call with request body as {string} to {string}", (String requestBody, String endpoint) -> {
            RequestSpecification reqSpec = given()
                    .header("Authorization", "Bearer " + sharedResponse.getToken()).contentType("application/json");

            response = reqSpec.log().all().body(requestBody).post(endpoint);

            sharedResponse.setResponse(response);

        });

        When("validate street2 column in address table in field operations database for member ID {string}", (String memberId) -> {

            String dbResponse = DbUtils.getAddressByQuery(DBQueries.getAddressStreet2(memberId));

            assertThat(dbResponse).isNotEqualTo(sharedResponse.getStreet2());

        });

        When("validate street2 column in address table in field operations database for member ID {string} should matches member service database", (String memberId) -> {


            String dbResponse = DbUtils.getAddressByQuery(DBQueries.getAddressStreet2(memberId));

            assertThat(dbResponse).isEqualTo(sharedResponse.getStreet2());

        });

        When("validate address column in email table in field operations database for member ID {string} should matches member service database", (String memberId) -> {


            String dbResponse = DbUtils.getEmailByQuery(DBQueries.getEmailAddress(memberId));

            assertThat(dbResponse).isEqualTo(sharedResponse.getEmailAddress());

        });

        When("validate dial number column in phone table in field operations database for member ID {string} should matches member service database", (String memberId) -> {


            String dbResponse = DbUtils.getPhoneByQuery(DBQueries.getPhoneDialNumber(memberId));

            assertThat(dbResponse).isEqualTo(sharedResponse.getPhoneNumber());

        });

        When("validate notes column in member appointment table in field operations database requested member ID should matches member service database", () -> {

            MemberAppointmentDTO dto = sharedResponse.getMemberAppointmentDTO();

            String dbResponse = DbUtils.getMemberAppoinmentByQuery(DBQueries.getMemberAppointmentFO(dto.getMemberId()));

            assertThat(dbResponse.replaceAll("[\\[\\]]", "")).isEqualToIgnoringCase(sharedResponse.getNotes().trim());

        });
    }
}