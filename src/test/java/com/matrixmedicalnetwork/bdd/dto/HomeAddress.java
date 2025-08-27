package com.matrixmedicalnetwork.bdd.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@JsonIgnoreProperties(ignoreUnknown = true)
@Data

public class HomeAddress {

    private int id;
    @JsonProperty("street1")
    private String startStreet1;
    @JsonProperty("city")
    private String startCity;
    @JsonProperty("state")
    private String startState;
    @JsonProperty("zip")
    private String startZip;
    private String lunchTime;
    @JsonProperty("duration")
    private String visitDuration;
    @JsonProperty("latitude")
    private String startLatitude;
    @JsonProperty("longitude")
    private String startLongitude;
    private String dateCreated;
    private String dateUpdated;
    private Territory territory;

}

