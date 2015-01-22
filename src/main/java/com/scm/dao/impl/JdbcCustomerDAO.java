package com.scm.dao.impl;

import com.scm.dao.CustomerDAO;
import com.scm.model.Customer;
import com.scm.model.CustomerRowMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

/**
 * Created by yholub on 1/22/2015.
 */
public class JdbcCustomerDAO extends SimpleJdbcDaoSupport implements CustomerDAO {

    private static final String CUSTOMER_SQL_INSERT = "INSERT INTO CUSTOMER (NAME, STATUS) VALUES (?, ?)";
    private static final String CUSTOMER_SQL_SELECT_BY_ID = "SELECT * FROM CUSTOMER WHERE ID = ?";

    @Override
    public void add(Customer customer) {
        getSimpleJdbcTemplate().update(
                CUSTOMER_SQL_INSERT, customer.getName(), customer.getStatus());
    }

    @Override
    public Customer getById(int customerId) {
        Customer customer;

        try {
            customer = getSimpleJdbcTemplate().queryForObject(
                    CUSTOMER_SQL_SELECT_BY_ID, new CustomerRowMapper(), customerId);
        } catch (EmptyResultDataAccessException ex) {
            customer = null;
        }
        return customer;
    }
}
