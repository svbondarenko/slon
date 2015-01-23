package com.scm.dao.impl;

import com.scm.dao.CustomerDAO;
import com.scm.model.Customer;
import com.scm.model.CustomerRowMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yholub on 1/22/2015.
 */
public class CustomerDAONamedImpl implements CustomerDAO {

    private static final String CUSTOMER_SQL_INSERT = "INSERT INTO CUSTOMER (NAME, STATUS) VALUES (:name, :status)";
    private static final String CUSTOMER_SQL_SELECT_ALL = "SELECT * FROM CUSTOMER";
    private static final String CUSTOMER_SQL_SELECT_BY_ID = "SELECT * FROM CUSTOMER WHERE ID = :id";

    private NamedParameterJdbcTemplate jdbcTemplateObject;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplateObject = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public void add(Customer customer) {
        Map<String, Object> namedParameters = new HashMap<String, Object>();
        namedParameters.put("name", customer.getName());
        namedParameters.put("status", customer.getStatus());
        jdbcTemplateObject.update(CUSTOMER_SQL_INSERT, namedParameters);
    }

    @Override
    public void add(List<Customer> customers) {
        List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
        for (Customer customer : customers) {
            SqlParameterSource parameter = new MapSqlParameterSource()
                    .addValue("name", customer.getName())
                    .addValue("status", customer.getStatus());
            parameters.add(parameter);
        }
        jdbcTemplateObject.batchUpdate(CUSTOMER_SQL_INSERT,
                parameters.toArray(new SqlParameterSource[parameters.size()]));
    }

    @Override
    public Customer getById(int customerId) {
        Customer customer;
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("id", customerId);

        try {
            customer = jdbcTemplateObject.queryForObject(
                    CUSTOMER_SQL_SELECT_BY_ID, map, new CustomerRowMapper());
        } catch (EmptyResultDataAccessException ex) {
            customer = null;
        }

        return customer;
    }

    @Override
    public List<Customer> getAll() {
        List<Customer> customers;

        try {
            customers = jdbcTemplateObject.query(CUSTOMER_SQL_SELECT_ALL, new CustomerRowMapper());
        } catch (EmptyResultDataAccessException ex) {
            customers = null;
        }

        return customers;
    }
}
