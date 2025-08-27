package com.matrixmedicalnetwork.bdd.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;


@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Availability {

    private boolean available;
    private String StartDate;
    private String endDate;
}
