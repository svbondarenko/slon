package com.scm.dao.impl;

import com.scm.dao.CustomerDAO;
import com.scm.model.Customer;
import com.scm.model.CustomerRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yholub on 1/22/2015.
 */
public class CustomerDAONamedImpl implements CustomerDAO {

    private static final String CUSTOMER_SQL_INSERT = "INSERT INTO customer (name, status) VALUES (:name, :status)";
    private static final String CUSTOMER_SQL_INSERT_GENERATED_KEY = "id";
    private static final String CUSTOMER_SQL_SELECT_ALL = "SELECT * FROM customer";
    private static final String CUSTOMER_SQL_SELECT_BY_ID = "SELECT * FROM customer WHERE id = :id";

    private NamedParameterJdbcTemplate jdbcTemplateObject;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplateObject = new NamedParameterJdbcTemplate(dataSource);
    }

    /**
     * @param customer customer data to insert
     * @return generated customer id on success or -1 on failure
     */
    @Override
    public long add(Customer customer) {
        Map<String, Object> namedParameters = new HashMap<String, Object>();
        namedParameters.put("name", customer.getName());
        namedParameters.put("status", customer.getStatus());

        MapSqlParameterSource sqlParameters = new MapSqlParameterSource().addValues(namedParameters);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int result = jdbcTemplateObject.update(CUSTOMER_SQL_INSERT, sqlParameters,
                keyHolder, new String[]{CUSTOMER_SQL_INSERT_GENERATED_KEY});

        return result > 0 ? keyHolder.getKey().longValue() : -1;
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
        Map<String, Integer> parameterMap = new HashMap<String, Integer>();
        parameterMap.put("id", customerId);
        return jdbcTemplateObject.queryForObject(CUSTOMER_SQL_SELECT_BY_ID, parameterMap, new CustomerRowMapper());
    }

    @Override
    public List<Customer> getAll() {
        return jdbcTemplateObject.query(CUSTOMER_SQL_SELECT_ALL, new CustomerRowMapper());
    }
}
