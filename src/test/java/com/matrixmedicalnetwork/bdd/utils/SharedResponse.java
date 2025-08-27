package com.matrixmedicalnetwork.bdd.utils;

import com.matrixmedicalnetwork.bdd.dto.MemberAppointmentDTO;
import io.restassured.response.Response;
import lombok.Data;

@Data
public class SharedResponse {

    private Response response;
    private String token;
    private String emailAddress;
    private String memberServiceToken;
    private String endpoint;
    private Response memberServiceResponse;
    private String street2;
    private String PhoneNumber;
    private MemberAppointmentDTO memberAppointmentDTO;
    private String notes;

}

