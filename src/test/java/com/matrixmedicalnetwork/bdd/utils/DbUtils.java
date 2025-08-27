package com.matrixmedicalnetwork.bdd.utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.matrixmedicalnetwork.bdd.dto.*;

public class DbUtils {

    public static List<MemberDTO> getMemberByQuery(String query) throws SQLException {
        List<MemberDTO> members = new ArrayList<>();

        return executeQuery(query, rs -> {

            while (rs.next()) {
                MemberDTO m = new MemberDTO();
                m.setId(rs.getLong("member_id"));
                m.setFirstName(rs.getString("first"));
                m.setMiddleName(rs.getString("middle"));
                m.setLastName(rs.getString("last"));
                m.setEmail(rs.getString("address"));

                Phone phone = new Phone();
                phone.setId(rs.getLong("phone_id"));
                phone.setNumber(rs.getString("dial_Number"));
                phone.setType(rs.getString("phone_type_id"));
                // phone.setPrimary(rs.getBoolean("primary"));
                phone.setDataSource(rs.getString("data_source_id"));

                m.setPhones(Collections.singletonList(phone));

                Address address = new Address();
                address.setMemberId(rs.getLong("member_id"));
                address.setId(rs.getLong("address_id"));
                address.setStreet1(rs.getString("street1"));
                address.setStreet2(rs.getString("street2"));
                address.setCity(rs.getString("city"));
                address.setState(rs.getString("state"));
                address.setZip(rs.getString("zip"));
                address.setTimeZone(rs.getString("iana_id"));
                address.setLatitude(rs.getDouble("latitude"));
                address.setLongitude(rs.getDouble("longitude"));

                Territory territory = new Territory();
                territory.setId(rs.getLong("code"));
                territory.setName(rs.getString("modified_name"));
                territory.setRegion(rs.getString("region"));
                territory.setTimeZone(rs.getString("iana_id"));

                address.setTerritory(territory);
                address.setType(rs.getString("address_type_id"));
                address.setDataSource(rs.getString("data_source_id"));

                m.setAddress(address);

                Client client = new Client();

                client.setId(rs.getLong("client_id"));
                client.setName(rs.getString("client_name"));
                client.setShortName(rs.getString("short_name"));

                m.setClient(client);

                members.add(m);
            }
            return members;

        });
    }

    public static List<ProviderDTO> getProviderIdByQuery(String query) throws SQLException {
        List<ProviderDTO> provider = new ArrayList<>();

        return executeQuery(query, rs -> {

            while (rs.next()) {
                ProviderDTO p = new ProviderDTO();
                p.setId(rs.getString("provider_id"));
                p.setEmployeeId(rs.getLong("employee_id"));
                p.setFirst(rs.getString("first"));
                p.setMiddle(rs.getString("middle"));
                p.setLast(rs.getString("last"));
                p.setPhone(rs.getString("dial_number"));
                p.setEmail(rs.getString("address"));
                p.setEmploymentType(rs.getString("status_id"));
                p.setRoleType(rs.getString("type_id"));
                //employment type missing
                //role type missing
                HomeAddress homeAddress = new HomeAddress();
                homeAddress.setId(rs.getInt("id"));
                // source missing
                homeAddress.setStartStreet1(rs.getString("start_street1"));
               // homeAddress.setStartStreet2(rs.getString("start_street2"));
                homeAddress.setStartCity(rs.getString("start_city"));
                homeAddress.setStartState(rs.getString("start_state"));
                homeAddress.setStartZip(rs.getString("start_zip"));
                homeAddress.setLunchTime(rs.getString("lunch_time"));
                homeAddress.setVisitDuration(rs.getString("visit_duration"));
                homeAddress.setStartLatitude(rs.getString("start_latitude"));
                homeAddress.setStartLongitude(rs.getString("start_longitude"));
                homeAddress.setDateCreated(rs.getString("date_created"));
               homeAddress.setDateUpdated(rs.getString("date_updated"));

                Territory territory = new Territory();
                territory.setId(rs.getLong("code"));
                territory.setName(rs.getString("modified_name"));
                territory.setTimeZone(rs.getString("iana_id"));
                territory.setRegion(rs.getString("region"));

                homeAddress.setTerritory(territory);
                p.setHomeAddress(homeAddress);

                String overrideQuery = DBQueries.getLocationOverrideByProviderId(p.getId());

                List<LocationOverride> overrides = executeQuery(overrideQuery, rs2 -> {
                    List<LocationOverride> list = new ArrayList<>();
                    while (rs2.next()) {

                        LocationOverride locationOverride = new LocationOverride();
                        locationOverride.setStartDate(rs2.getString("start_date"));
                        locationOverride.setEndDate(rs2.getString("end_date"));

                        Address startAddr = new Address();
                        startAddr.setId(rs2.getInt("id"));
                        // startAddr.setStartAddress(rs2.getString("start_address"));
                        //startAddr.setEndAddress(rs2.getString("end_address"));
                        startAddr.setStreet1(rs2.getString("start_street1"));
                        //  startAddr.setStreet2(rs.getString("start_street2"));
                        startAddr.setCity(rs2.getString("start_city"));
                        startAddr.setState(rs2.getString("start_state"));
                        startAddr.setZip(rs2.getString("start_zip"));
                        startAddr.setTimeZone(rs2.getString("iana_id"));
                        startAddr.setLatitude(rs2.getDouble("start_latitude"));
                        startAddr.setLongitude(rs2.getDouble("start_longitude"));
                   //     startAddr.setGeocodingConfidence(rs2.getString("geocoding_confidence"));

                        Territory territory1 = new Territory();
                        territory1.setId(rs2.getLong("code"));
                        territory1.setName(rs2.getString("modified_name"));
                        territory1.setTimeZone(rs2.getString("iana_id"));
                        territory1.setRegion(rs2.getString("region"));

                        startAddr.setTerritory(territory1);
//                        List<Address> startAddress = new ArrayList<>();
//                        startAddress.add(startAddr);
                        locationOverride.setStartAddress(startAddr);

                        Address endAddr = new Address();
                        endAddr.setId(rs2.getLong("id"));

                        endAddr.setStreet1(rs2.getString("end_street1"));
                        //        endAddr.setStreet2(rs2.getString("start_street2"));
                        endAddr.setCity(rs2.getString("end_city"));
                        endAddr.setState(rs2.getString("end_state"));
                        endAddr.setZip(rs2.getString("end_zip"));
                        endAddr.setTimeZone(rs2.getString("iana_id"));
                        endAddr.setLatitude(rs2.getDouble("end_latitude"));
                        endAddr.setLongitude(rs2.getDouble("end_longitude"));
        //                    endAddr.setGeocodingConfidence(rs2.getString("geocoding_confidence"));

                        Territory territory2 = new Territory();
                        territory2.setId(rs2.getLong("code"));
                        territory2.setName(rs2.getString("modified_name"));
                        territory2.setTimeZone(rs2.getString("iana_id"));
                        territory2.setRegion(rs2.getString("region"));

                        startAddr.setTerritory(territory2);
//                        List<Address> endAddress = new ArrayList<>();
//                        endAddress.add(endAddr);
                        locationOverride.setEndAddress(endAddr);
                        list.add(locationOverride);

                    }
                    return list;

                });

                if (p.getLocationOverrides() == null) {
                    p.setLocationOverrides(new ArrayList<>());
                }

                p.getLocationOverrides().addAll(overrides);

                provider.add(p);
            }

            return provider;
        });
    }

    public static String getAddressByQuery(String query) throws SQLException {
        return executeQuery(query, rs -> {
            if (rs.next()) {
                return rs.getString("street2");
            }

            return null;
        });
    }

    public static String getEmailByQuery(String query) throws SQLException {
        return executeQuery(query, rs -> {
            if (rs.next()) {
                return rs.getString("address");
            }

            return null;
        });
    }

    public static String getPhoneByQuery(String query) throws SQLException {
        return executeQuery(query, rs -> {
            if (rs.next()) {
                return rs.getString("dial_number");
            }

            return null;
        });
    }

    public static Integer getProviderByQuery(String query) throws SQLException {
        return executeQuery(query, rs -> {
            while (rs.next()) {
                return rs.getInt("total");
            }
            return 0;
        });
    }

    public static String getMemberAppoinmentByQuery(String query) throws SQLException {
        return executeQuery(query, rs -> {
            if (rs.next()) {
                return rs.getString("notes");
            }

            return null;
        });
    }

    public static Integer getWorkOrderByQuery(String query) throws SQLException {
        return executeQuery(query, rs -> {
            while (rs.next()) {
                return rs.getInt("total");
            }
            return 0;
        });
    }

    public static MemberAppointmentDTO getMemberAppointmentByQuery(String query) throws SQLException {

        try (Connection connection = DBConnection.getConnectionMemberService();

             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet rs = statement.executeQuery()) {

            if (rs.next()) {
                MemberAppointmentDTO ma = new MemberAppointmentDTO();
                ma.setMemberId(rs.getString("memberid"));
                ma.setSourceId(rs.getString("source_id"));
                return ma;
            }
        }
        return null;
    }


    @FunctionalInterface
    public interface ResultSetHandler<T> {
        T handle(ResultSet rs) throws SQLException;
    }

    private static <T> T executeQuery(String sql, ResultSetHandler<T> handler) throws SQLException {
        try (Connection connection = DBConnection.getConnection();

             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {
            return handler.handle(rs);
        }
    }
}



