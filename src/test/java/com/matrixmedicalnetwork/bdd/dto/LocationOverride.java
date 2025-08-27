package com.matrixmedicalnetwork.bdd.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
@Data

public class LocationOverride {

    private String startDate;
    private String endDate;
    private Address startAddress;
    private Address endAddress;

}
