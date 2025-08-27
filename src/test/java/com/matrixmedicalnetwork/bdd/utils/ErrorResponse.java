package com.matrixmedicalnetwork.bdd.utils;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorResponse {

    private String error;

    @JsonProperty("error_codes")
    private List<Integer> errorCodes;

}
