package com.matrixmedicalnetwork.bdd.stepdefinitions;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import com.matrixmedicalnetwork.bdd.base.BaseTest;
import com.matrixmedicalnetwork.bdd.dto.MemberDTO;
import com.matrixmedicalnetwork.bdd.dto.Phone;
import com.matrixmedicalnetwork.bdd.utils.DBQueries;
import com.matrixmedicalnetwork.bdd.utils.DbUtils;
import com.matrixmedicalnetwork.bdd.utils.SharedRequest;
import com.matrixmedicalnetwork.bdd.utils.SharedResponse;

import org.json.JSONObject;
import io.cucumber.java8.En;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class MemberSteps extends BaseTest implements En {

    private Response response;

    public MemberSteps(SharedRequest sharedRequest, SharedResponse sharedResponse) {

        When("the client sends a GET request to {string}", (String endpoint) -> {
            RequestSpecification reqSpec = given().queryParam("page", 1).queryParam("pageSize", 35)
                    .header("Authorization", "Bearer " + sharedResponse.getToken()).contentType("application/json");

            response = reqSpec.get(endpoint);

            sharedResponse.setResponse(response);

        });

        Then("the API response should match the database response", () -> {

            List<MemberDTO> dbResponse = DbUtils.getMemberByQuery(DBQueries.getMember());
            List<MemberDTO> apiResponse = sharedResponse.getResponse().jsonPath().getList("records", MemberDTO.class);

            for (int i = 0; i < apiResponse.size(); i++) {
                MemberDTO actual = apiResponse.get(i);
                MemberDTO expected = dbResponse.get(i);

                assertThat(actual).usingRecursiveComparison()
                        .ignoringFields("address", "address.source", "skills", "email", "phones").isEqualTo(expected);

                List<Phone> expectedPhones = expected.getPhones();
                List<Phone> actualPhones = actual.getPhones();

                for (Phone expectedPhone : expectedPhones) {
                    boolean matchFound = actualPhones.stream()
                            .anyMatch(apiPhone -> apiPhone.getNumber().equals(expectedPhone.getNumber())
                                    && apiPhone.getType().equals(expectedPhone.getType())
                                    && apiPhone.isPrimary() == expectedPhone.isPrimary()
                                    && apiPhone.getDataSource().equals(expectedPhone.getDataSource()));

                }
            }

        });

        Then("the API response should match the database record for member ID {string}", (String memberId) -> {

            List<MemberDTO> dbResponse = DbUtils.getMemberByQuery(DBQueries.getMemberById(memberId));
            List<MemberDTO> apiResponse = sharedResponse.getResponse().jsonPath().getList("records", MemberDTO.class);

            for (int i = 0; i < apiResponse.size(); i++) {
                MemberDTO actual = apiResponse.get(i);
                MemberDTO expected = dbResponse.get(i);

                assertThat(apiResponse.size()).isEqualTo(dbResponse.size());
                assertThat(actual).usingRecursiveComparison()
                        .ignoringFields("address.source", "skills", "phones", "email").isEqualTo(expected);

                List<Phone> expectedPhones = expected.getPhones();
                List<Phone> actualPhones = actual.getPhones();

                for (Phone expectedPhone : expectedPhones) {
                    boolean matchFound = actualPhones.stream()
                            .anyMatch(apiPhone -> apiPhone.getNumber().equals(expectedPhone.getNumber())
                                    && apiPhone.getType().equals(expectedPhone.getType())
                                    && apiPhone.isPrimary() == expectedPhone.isPrimary()
                                    && apiPhone.getDataSource().equals(expectedPhone.getDataSource()));

                    assertThat(matchFound).withFailMessage("phone not found in api response:" + expectedPhone).isTrue();
                }
            }

        });

    }
}
