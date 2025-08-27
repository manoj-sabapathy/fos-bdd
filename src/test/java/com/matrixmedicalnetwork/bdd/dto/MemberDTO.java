package com.matrixmedicalnetwork.bdd.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class MemberDTO {

    private long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private List<Phone> phones;
    private String email;
    private Address address;
    private Client client;
    // private List<String> skills;
}