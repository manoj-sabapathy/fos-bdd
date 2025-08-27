package com.matrixmedicalnetwork.bdd.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ProviderDTO {

    private String id;
    private long employeeId;
    @JsonProperty("firstName")
    private String first;
    private String middle;
    @JsonProperty("lastName")
    private String last;
    @JsonProperty("phoneNumber")
    private String phone;
    private String email;
    private String employmentType;
    private String roleType;
    private HomeAddress homeAddress;
    // private List<Skills> skills;
    // private Territory territory;
    private List<LocationOverride> locationOverrides;
    //private List<Availability> availability;

    public String getRoleType() {
        if ("15".equals(roleType)) {
            return "Practitioner";

        } else if ("114".equals(roleType)) {
            return "LicensedPracticalNurse";

        } else if ("111".equals(roleType)) {
            return "RegisteredNurse";
        }
        return roleType;
    }

    public String getEmploymentType() {
        if ("1".equals(employmentType)) {
            return "1099 Flex";

        }
        return employmentType;
    }

}

