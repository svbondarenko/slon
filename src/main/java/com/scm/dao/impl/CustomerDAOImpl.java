package com.scm.dao.impl;

import com.scm.dao.CustomerDAO;
import com.scm.model.Customer;
import com.scm.model.CustomerRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yholub on 1/22/2015.
 */
public class CustomerDAOImpl  implements CustomerDAO {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    private static final String CUSTOMER_SQL_INSERT = "INSERT INTO CUSTOMER (NAME, STATUS) VALUES (?, ?)";
    private static final String CUSTOMER_SQL_SELECT_ALL = "SELECT * FROM CUSTOMER";
    private static final String CUSTOMER_SQL_SELECT_BY_ID = "SELECT * FROM CUSTOMER WHERE ID = ?";

    @Override
    public void add(Customer customer) {
        jdbcTemplateObject.update(
                CUSTOMER_SQL_INSERT, customer.getName(), customer.getStatus());
    }

    @Override
    public void add(List<Customer> customers) {
        List<Object[]> batchArgs = new ArrayList<Object[]>();
        for (Customer customer : customers) {
            batchArgs.add(new Object[] {customer.getName(), customer.getStatus()});
        }
        jdbcTemplateObject.batchUpdate(CUSTOMER_SQL_INSERT, batchArgs);
    }

    @Override
    public Customer getById(int customerId) {
        Customer customer;

        try {
            customer = jdbcTemplateObject.queryForObject(
                    CUSTOMER_SQL_SELECT_BY_ID, new CustomerRowMapper(), customerId);
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
