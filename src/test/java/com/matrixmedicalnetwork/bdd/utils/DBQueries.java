package com.matrixmedicalnetwork.bdd.utils;

import java.sql.ResultSet;

public class DBQueries {

    public static String getMember() {
        return "with latest_phone as \n" + "( select * from (\n"
                + "select *, ROW_NUMBER() over (partition by member_id order by date_updated desc, id DESC) As rn \n"
                + "from phone)\n" + "phone_filtered \n" + "where rn=1\n" + "),\n" + "latest_address as\n"
                + "( select * from (\n"
                + "select *, ROW_NUMBER() over (partition by member_id order by date_updated desc, id DESC) As rn \n"
                + "from address)\n" + "address_filtered \n" + "where rn=1\n" + "),\n"
                + "zip_statistical_area_latest_ as\n" + "(select distinct on (zip)* \n"
                + "from zip_statistical_area zsa\n" + "order by zip, date_updated desc \n" + "),\n"
                + "member_data_final as (\n" + "SELECT distinct on(m.id)\n" + "m.id as member_id ,\n" + "md.first,\n"
                + "md.middle,\n" + "md.last,\n" + "p.id as phone_id,\n" + "p.dial_number,\n"
                + "p.type_id as phone_type_id,\n" + "e.address,\n" + "a.id as address_id,\n" + "a.data_source_id,\n"
                + "a.street1 ,\n" + "a.street2,\n" + "a.city,\n" + "a.state, \n" + "a.zip ,\n" + "t.iana_id,\n"
                + "a.latitude, \n" + "a.longitude,\n" + "a.geocoding_confidence,\n" + "zsa.code,\n" + "zsa.modified_name,\n" + "t.iana_id,\n"
                + "zsa.region,\n" + "a.type_id as address_type_id,\n" + "a.data_source_id,\n" + "m.client_id,\n"
                + "c.name AS client_name,\n" + "c.short_name\n" + "from member m\n"
                + "left join member_demographic md on md.member_id = m.id \n"
                + "left join email e on md.member_id = e.member_id\n" + "left join client c on c.id = m.client_id\n"
                + "left join latest_phone p on p.member_id = m.id\n"
                + "left join latest_address a on a.member_id = m.id\n" + "left join timezone t on a.zip = t.zip \n"
                + "left join zip_statistical_area zsa on zsa.zip = a.zip\n" + ")\n"
                + "select * from member_data_final\n" + "order by first asc nulls last";

    }

    public static String getMemberById(String memberId) {
        return "with latest_phone as \n" + "( select * from (\n"
                + "select *, ROW_NUMBER() over (partition by member_id order by date_updated desc, id DESC) As rn \n"
                + "from phone)\n" + "phone_filtered \n" + "where rn=1\n" + "),\n" + "latest_address as\n"
                + "( select * from (\n"
                + "select *, ROW_NUMBER() over (partition by member_id order by date_updated desc, id DESC) As rn \n"
                + "from address)\n" + "address_filtered \n" + "where rn=1\n" + "),\n"
                + "zip_statistical_area_latest_ as\n" + "(select distinct on (zip)* \n"
                + "from zip_statistical_area zsa\n" + "order by zip, date_updated desc \n" + "),\n"
                + "member_data_final as (\n" + "SELECT distinct on(m.id)\n" + "m.id as member_id ,\n" + "md.first,\n"
                + "md.middle,\n" + "md.last,\n" + "p.id as phone_id,\n" + "p.dial_number,\n"
                + "p.type_id as phone_type_id,\n" + "e.address,\n" + "a.id as address_id,\n" + "a.data_source_id,\n"
                + "a.street1 ,\n" + "a.street2,\n" + "a.city,\n" + "a.state, \n" + "a.zip ,\n" + "t.iana_id,\n"
                + "a.latitude, \n" + "a.longitude,\n" + "a.geocoding_confidence,\n" + "zsa.code,\n" + "zsa.modified_name,\n" + "t.iana_id,\n"
                + "zsa.region,\n" + "a.type_id as address_type_id,\n" + "a.data_source_id,\n" + "m.client_id,\n"
                + "c.name AS client_name,\n" + "c.short_name\n" + "from member m\n"
                + "left join member_demographic md on md.member_id = m.id \n"
                + "left join email e on md.member_id = e.member_id\n" + "left join client c on c.id = m.client_id\n"
                + "left join latest_phone p on p.member_id = m.id\n"
                + "left join latest_address a on a.member_id = m.id\n" + "left join timezone t on a.zip = t.zip \n"
                + "left join zip_statistical_area zsa on zsa.zip = a.zip\n" + "where m.id = '" + memberId + "'" + ")\n"
                + "select * from  member_data_final \n" + "order by first asc nulls last";

    }

    public static String getAddressStreet2(String memberId) {
        return "select member_id, street2 from address where member_id = '" + memberId + "' order by date_created desc limit 1";

    }

    public static String getEmailAddress(String memberId) {
        return "select member_id, address from email where member_id = '" + memberId + "' order by date_created desc limit 1";

    }

    public static String getPhoneDialNumber(String memberId) {
        return "select dial_number from phone where member_id = '" + memberId + "' order by date_created desc limit 1";

    }

    public static String getMemberAppointment() {
        return "select * from member_appointment where calendar_date is not null order by dateupdated desc limit 1";

    }


    public static String getProviderCount() {
        return "select count(*) as total from provider";

    }


    public static String getWorkOrderCount() {
        return "select count(*) as total from work_order";

    }

    public static String getMemberAppointmentFO(String memberId) {
        return "select notes from member_appointment where member_id = '" + memberId + "'";

    }

    public static String getProviderId(String providerId) {
        return "SELECT distinct on(pd.id)\n" +
                "pd.id as provider_id ,\n" +
                "pd.employee_id,\n" +
                "pd.first ,\n" +
                "pd.middle,\n" +
                "pd.last,\n" +
                "p.dial_number,\n" +
                "e.address,\n" +
                "pes.status_id,\n" +
                "pr.type_id,\n" +
                "psl.id,\n" +
                "psl.start_street1,\n" +
                "psl.start_street2,\n" +
                "psl.start_city,\n" +
                "psl.start_state,\n" +
                "psl.start_zip,\n" +
                "t.iana_id,\n" +
                "psl.lunch_time,\n" +
                "psl.visit_duration,\n" +
                "psl.start_latitude,\n" +
                "psl.start_longitude,\n" +
                "psl.date_created,\n" +
                "psl.date_updated,\n" +
                "zsa.code,\n" +
                "zsa.modified_name,\n" +
                "t.iana_id,\n" +
                "zsa.region\n" +
                "from provider pd\n" +
                "left join email e on e.provider_id = pd.id \n" +
                "left join phone p on p.provider_id = pd.id  \n" +
                "left join provider_role pr on pr.provider_id =pd.id\n" +
                "left join provider_employment_status pes on pes.provider_id =pd.id\n" +
                "left join provider_schedule_location psl on psl.provider_id = pd.id\n" +
                "left join timezone t on psl.start_zip = t.zip \n" +
                "left join zip_statistical_area zsa on zsa.zip = psl.start_zip\n" +
                "where pd.id = '" + providerId + "'\n" +
                "order by pd.id";

    }

    public static String getLocationOverrideByProviderId(String providerId) {
        return "SELECT \n" +
                "pslo.start_date,\n" +
                "pslo.end_date,\n" +
                "pslo.id,\n" +
                "pslo.start_street1,\n" +
                "pslo.start_city,\n" +
                "pslo.start_state,\n" +
                "pslo.start_zip,\n" +
                "t.iana_id,\n" +
                "psl.start_latitude,\n" +
                "psl.start_longitude,\n" +
                "zsa.code,\n" +
                "zsa.modified_name,\n" +
                "t.iana_id,\n" +
                "zsa.code,\n" +
                "zsa.modified_name,\n" +
                "t.iana_id,\n" +
                "zsa.region,\n" +
                "pslo.end_street1,\n" +
                "pslo.end_city,\n" +
                "pslo.end_state,\n" +
                "pslo.end_zip,\n" +
                "t2.iana_id,\n" +
                "psl.end_latitude,\n" +
                "psl.end_longitude,\n" +
                "zsa2.code,\n" +
                "zsa2.modified_name,\n" +
                "t2.iana_id,\n" +
                "zsa.region\n" +
                "from provider pd\n" +
                "left join provider_schedule_location_override pslo on pslo.provider_id = pd.id\n" +
                "left join timezone t on pslo.start_zip = t.zip \n" +
                "left join timezone t2 on pslo.end_zip = t2.zip\n" +
                "left join provider_schedule_location psl on psl.provider_id = pd.id\n" +
                "left join zip_statistical_area zsa on zsa.zip = pslo.start_zip\n" +
                "left join zip_statistical_area zsa2 on zsa2.zip = pslo.end_zip\n" +
                "where pd.id = '" + providerId + "'\n" +
                "order by pslo.id desc";

    }

}