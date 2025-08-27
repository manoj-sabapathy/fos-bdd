package com.matrixmedicalnetwork.bdd.stepdefinitions;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import com.matrixmedicalnetwork.bdd.base.BaseTest;
import com.matrixmedicalnetwork.bdd.dto.LocationOverride;

import com.matrixmedicalnetwork.bdd.dto.ProviderDTO;
import com.matrixmedicalnetwork.bdd.utils.DBQueries;
import com.matrixmedicalnetwork.bdd.utils.DbUtils;
import com.matrixmedicalnetwork.bdd.utils.SharedRequest;
import com.matrixmedicalnetwork.bdd.utils.SharedResponse;

import io.cucumber.java8.En;

import io.restassured.response.Response;

public class ProviderSteps extends BaseTest implements En {

    private Response response;

    public ProviderSteps(SharedRequest sharedRequest, SharedResponse sharedResponse) {

        Then("the provider API and db total count should match", () -> {

            String dbResponse = String.valueOf(DbUtils.getProviderByQuery(DBQueries.getProviderCount()));


            assertThat(dbResponse).isEqualTo(sharedResponse.getResponse().jsonPath().getString("totalCount"));


        });


        Then("the API response response should match the database record for provider ID {string}", (String providerId) -> {

            List<ProviderDTO> dbResponse = DbUtils.getProviderIdByQuery(DBQueries.getProviderId(providerId));
            ProviderDTO apiResponse = sharedResponse.getResponse().as(ProviderDTO.class);


            assertThat(apiResponse.getId()).isEqualTo(dbResponse.get(0).getId());
            assertThat(apiResponse.getEmployeeId()).isEqualTo(dbResponse.get(0).getEmployeeId());
            assertThat(apiResponse.getFirst()).isEqualTo(dbResponse.get(0).getFirst());
            assertThat(apiResponse.getLast()).isEqualTo(dbResponse.get(0).getLast());
            assertThat(apiResponse.getPhone()).isEqualTo(dbResponse.get(0).getPhone());
            assertThat(apiResponse.getPhone()).isEqualTo(dbResponse.get(0).getPhone());
            assertThat(apiResponse.getEmail()).isEqualTo(dbResponse.get(0).getEmail());
            assertThat(apiResponse.getEmploymentType()).isEqualTo(dbResponse.get(0).getEmploymentType());
            assertThat(apiResponse.getRoleType()).isEqualTo(dbResponse.get(0).getRoleType());

            assertThat(apiResponse.getHomeAddress().getId()).isEqualTo(dbResponse.get(0).getHomeAddress().getId());
            assertThat(apiResponse.getHomeAddress().getStartStreet1()).isEqualTo(dbResponse.get(0).getHomeAddress().getStartStreet1());
            assertThat(apiResponse.getHomeAddress().getStartCity()).isEqualTo(dbResponse.get(0).getHomeAddress().getStartCity());
            assertThat(apiResponse.getHomeAddress().getStartState()).isEqualTo(dbResponse.get(0).getHomeAddress().getStartState());
            assertThat(apiResponse.getHomeAddress().getStartZip()).isEqualTo(dbResponse.get(0).getHomeAddress().getStartZip());
            assertThat(apiResponse.getHomeAddress().getLunchTime()).isEqualTo(dbResponse.get(0).getHomeAddress().getLunchTime());
            assertThat(apiResponse.getHomeAddress().getVisitDuration()).isEqualTo(dbResponse.get(0).getHomeAddress().getVisitDuration());
            assertThat(apiResponse.getHomeAddress().getStartLatitude()).isEqualTo(dbResponse.get(0).getHomeAddress().getStartLatitude());
            assertThat(apiResponse.getHomeAddress().getStartLongitude()).isEqualTo(dbResponse.get(0).getHomeAddress().getStartLongitude());
            assertThat(apiResponse.getHomeAddress().getTerritory().getId()).isEqualTo(dbResponse.get(0).getHomeAddress().getTerritory().getId());
            assertThat(apiResponse.getHomeAddress().getTerritory().getName()).isEqualTo(dbResponse.get(0).getHomeAddress().getTerritory().getName());
            assertThat(apiResponse.getHomeAddress().getTerritory().getTimeZone()).isEqualTo(dbResponse.get(0).getHomeAddress().getTerritory().getTimeZone());
            assertThat(apiResponse.getHomeAddress().getTerritory().getRegion()).isEqualTo(dbResponse.get(0).getHomeAddress().getTerritory().getRegion());

            List<LocationOverride> apiOverrides = apiResponse.getLocationOverrides();
            List<LocationOverride> dbOverrides = dbResponse.get(0).getLocationOverrides();

            assertThat(apiOverrides).hasSameSizeAs(dbOverrides);

        });
    }
}

