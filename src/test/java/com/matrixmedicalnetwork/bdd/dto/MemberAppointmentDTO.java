package com.matrixmedicalnetwork.bdd.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class MemberAppointmentDTO {
    private String memberId;
    private String sourceId;
}
