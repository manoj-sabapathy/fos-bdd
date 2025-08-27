package com.matrixmedicalnetwork.bdd.stepdefinitions;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import com.matrixmedicalnetwork.bdd.dto.MemberAppointmentDTO;
import com.matrixmedicalnetwork.bdd.utils.DBQueries;
import com.matrixmedicalnetwork.bdd.utils.DbUtils;
import com.matrixmedicalnetwork.bdd.utils.SharedResponse;
import com.matrixmedicalnetwork.bdd.utils.configLoader;

import io.cucumber.datatable.DataTable;
import io.cucumber.java8.En;
import io.restassured.response.Response;

import java.util.*;

public class CommonSteps implements En {

    private Response response;

    private final SharedResponse sharedResponse;

    public CommonSteps(SharedResponse sharedResponse) {
        this.sharedResponse = sharedResponse;

        Then("the response status code should be {int}", (Integer expectedStatusCode) -> {

            Response response = sharedResponse.getResponse();

            assertThat(response.getStatusCode()).isEqualTo(expectedStatusCode);
        });

        Given("the client has a valid OAuth token generated using client credentials", () -> {

            response = given().urlEncodingEnabled(true).formParam("client_id",
                            configLoader.getProperty("client_id")).formParam("client_secret", configLoader.getProperty("client_secret"))
                    .contentType("application/x-www-form-urlencoded")
                    .formParam("grant_type", configLoader.getProperty("grant_type"))
                    .formParam("scope", configLoader.getProperty("scope")).when()
                    .post(configLoader.getProperty("token_url"));

            String token = response.jsonPath().getString("access_token");

            sharedResponse.setResponse(response);

            sharedResponse.setToken(token);

        });


        Given("the client has a valid OAuth token generated using client credentials for member service", () -> {


            response = given().urlEncodingEnabled(true).formParam("client_id",
                            configLoader.getProperty("memberService_client_id")).formParam("client_secret", configLoader.getProperty("memberService_client_secret"))
                    .contentType("application/x-www-form-urlencoded")
                    .formParam("grant_type", configLoader.getProperty("memberService_grant_type"))
                    .formParam("scope", configLoader.getProperty("memberService_scope")).when()
                    .post(configLoader.getProperty("memberService_token_url"));

            String token = response.jsonPath().getString("access_token");

            sharedResponse.setMemberServiceToken(token);

        });


        When("the client sends a PUT request to {string} to update address", (String endpoint, DataTable dataTable) -> {


            String street2 = "APT" + (100 + new Random().nextInt(900));
            sharedResponse.setStreet2(street2);


            Map<String, String> data = dataTable.asMap(String.class, String.class);

            Map<String, Object> requestBody = new LinkedHashMap<>();

            requestBody.put("primary", Boolean.parseBoolean((data.get("primary"))));
            requestBody.put("addressType", data.get("addressType"));
            requestBody.put("dataSource", DataSource.fromString(data.get("dataSource")));
            requestBody.put("street1", data.get("street1"));
            requestBody.put("street2", street2);
            requestBody.put("city", data.get("city"));
            requestBody.put("state", data.get("state"));
            requestBody.put("zip", data.get("zip"));

            Response response = given().log().all().header("Authorization", "Bearer " + sharedResponse.getMemberServiceToken()).contentType("application/json").body(requestBody).put(endpoint);

            sharedResponse.setResponse(response);

        });

        When("the client sends a PUT request to {string} to update email", (String endpoint, DataTable dataTable) -> {


            String emailAddress = UUID.randomUUID().toString().substring(0, 5) + "@matrix.com";
            sharedResponse.setEmailAddress(emailAddress);

            Map<String, String> data = dataTable.asMap(String.class, String.class);

            Map<String, Object> requestBody = new LinkedHashMap<>();

            requestBody.put("emailType", data.get("emailType"));
            requestBody.put("dataSource", DataSource.fromString(data.get("dataSource")));
            requestBody.put("emailAddress", sharedResponse.getEmailAddress());
            requestBody.put("primary", Boolean.parseBoolean((data.get("primary"))));
            requestBody.put("telehealth", Boolean.parseBoolean((data.get("telehealth"))));
            requestBody.put("emailGrade", data.get("emailGrade"));
            requestBody.put("emailConsentType", Boolean.parseBoolean((data.get("emailConsentType"))));

            Response response = given().log().all().header("Authorization", "Bearer " + sharedResponse.getMemberServiceToken()).contentType("application/json").body(requestBody).put(endpoint);

            sharedResponse.setResponse(response);

        });

        When("the client sends a PUT request to {string} to update phone number", (String endpoint, DataTable dataTable) -> {


            Random random = new Random();
            int area = random.nextInt(800) + 200;
            int exchange = random.nextInt(800) + 200;
            int line = random.nextInt(10000);
            String phoneNumber = String.format("%03d%03d%04d", area, exchange, line);
            sharedResponse.setPhoneNumber(phoneNumber);


            Map<String, String> data = dataTable.asMap(String.class, String.class);

            Map<String, Object> requestBody = new LinkedHashMap<>();

            requestBody.put("number", sharedResponse.getPhoneNumber());
            requestBody.put("telephoneType", data.get("telephoneType"));
            requestBody.put("dataSource", DataSource.fromString(data.get("dataSource")));
            requestBody.put("extension", data.get("extension"));
            requestBody.put("primary", Boolean.parseBoolean(data.get("primary")));
            requestBody.put("consentToCall", Boolean.parseBoolean((data.get("consentToCall"))));
            requestBody.put("consentToSms", Boolean.parseBoolean((data.get("consentToSms"))));
            requestBody.put("textable", Boolean.parseBoolean((data.get("textable"))));
            requestBody.put("clientSuppliedDno", Boolean.parseBoolean((data.get("clientSuppliedDno"))));
            requestBody.put("telehealth", Boolean.parseBoolean((data.get("telehealth"))));

            Response response = given().log().all().header("Authorization", "Bearer " + sharedResponse.getMemberServiceToken()).contentType("application/json").body(requestBody).put(endpoint);

            sharedResponse.setResponse(response);

        });


        When("the client sends a PUT request to {string} to update appointment details", (String endpoint, DataTable dataTable) -> {

            String dbResponse = String.valueOf(DbUtils.getMemberAppointmentByQuery(DBQueries.getMemberAppointment()));

            MemberAppointmentDTO dto = DbUtils.getMemberAppointmentByQuery(DBQueries.getMemberAppointment());
            sharedResponse.setMemberAppointmentDTO(dto);

            dto = sharedResponse.getMemberAppointmentDTO();

            Map<String, String> data = dataTable.asMap(String.class, String.class);

            Map<String, Object> requestBody = new LinkedHashMap<>();

            requestBody.put("appointmentId", dto.getSourceId());
            requestBody.put("cohabMemberId", data.get("cohabMemberId"));
            requestBody.put("activityType", data.get("activityType"));
            requestBody.put("disposition", data.get("disposition"));
            requestBody.put("dispositionReason", data.get("dispositionReason"));
            requestBody.put("subject", data.get("subject"));
            requestBody.put("notes", data.get("notes"));
            requestBody.put("dataSource", DataSource.fromString(data.get("dataSource")));

            Response response = given().log().all().header("Authorization", "Bearer " + sharedResponse.getMemberServiceToken()).contentType("application/json").body(requestBody).put(endpoint + dto.getMemberId());
            sharedResponse.setNotes(response.getBody().jsonPath().getString("notes"));

            sharedResponse.setResponse(response);

        });

    }

    public enum DataSource {
        Client, SelfReported, Vendor, Carenet;

        public static DataSource fromString(String input) {
            for (DataSource ds : DataSource.values()) {
                if (ds.name().equalsIgnoreCase(input.trim())) {
                    return ds;
                }
            }
            return null;
        }
    }


}
