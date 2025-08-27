package com.matrixmedicalnetwork.bdd.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;


@JsonIgnoreProperties(ignoreUnknown = true)
@Data

public class ProviderScheduleTime {

    private String working;
    private String daysOfWeek;
    private String preferredStartTime;
    private String preferredEndTime;
}
