package com.scm.model;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by yholub on 1/22/2015.
 */
public class CustomerRowMapper implements ParameterizedRowMapper<Customer> {

    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {

        int id = rs.getInt("ID");
        String name = rs.getString("NAME");
        int status = rs.getInt("STATUS");

        return new Customer(id, name, status);
    }

}
