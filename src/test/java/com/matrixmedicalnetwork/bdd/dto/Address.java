package com.matrixmedicalnetwork.bdd.dto;

import lombok.Data;

@Data
public class Address {

    private long id;
    private long memberId;
    private String source;
    private String street1;
    private String street2;
    private String city;
    private String state;
    private String zip;
    private String timeZone;
    private double latitude;
    private double longitude;
    private String geocodingConfidence;
    private Territory territory;
    private String type;
    private String dataSource;
    private String StartAddress;
    private String EndAddress;
    private String dateCreated;
    private String dateUpdated;

    public String getType() {
        if ("1".equals(type)) {
            return "Physical";
        } else if ("2".equals(type)) {
            return "Mailing";
        }
        return type;
    }

    public String getDataSource() {
        if ("2".equals(dataSource)) {
            return "Client";
        } else if ("3".equals(dataSource)) {
            return "Self-Reported";
        } else if ("6".equals(dataSource)) {
            return "Vendor";
        }

        return dataSource;
    }

    public String getStreet2() {
        if (street2 == null) {
            return null;
        }

        if (street2.isEmpty()) {
            return null;

        }
        return street2;
    }

    public String getGeocodingConfidence() {
        if ("0".equals(geocodingConfidence)) {
            return "unknown";
        } else if ("1".equals(geocodingConfidence)) {
            return "Low";
        } else if ("2".equals(geocodingConfidence)) {
            return "Medium";
        } else if ("3".equals(geocodingConfidence)) {
            return "High";
        } else if ("4".equals(geocodingConfidence)) {
            return "Exact";
        }
        return "geocodingConfidence";
    }


}
