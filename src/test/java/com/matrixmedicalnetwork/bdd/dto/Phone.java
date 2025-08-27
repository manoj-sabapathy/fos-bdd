package com.matrixmedicalnetwork.bdd.dto;

import lombok.Data;

@Data
public class Phone {
    private long id;
    private String number;
    private String type;
    private String dataSource;
    private boolean primary;

    public String getType() {

        if ("1".equals(type)) {
            return "Primary";

        } else if ("4".equals(type)) {
            return "Home";

        } else if ("5".equals(type)) {
            return "Work";

        } else if ("6".equals(type)) {
            return "WorkCell";

        } else if ("7".equals(type)) {
            return "Personal Cell";
        }

        return type;
    }

    public String getDataSource() {

        if ("3".equals(dataSource)) {
            return "SelfReported";

        } else if ("2".equals(dataSource)) {
            return "Client";

        }

        return dataSource;
    }

}
