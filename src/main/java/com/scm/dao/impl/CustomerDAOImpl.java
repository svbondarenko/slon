package com.scm.dao.impl;

import com.scm.dao.CustomerDAO;
import com.scm.model.Customer;
import com.scm.model.CustomerRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yholub on 1/22/2015.
 */
public class CustomerDAOImpl implements CustomerDAO {

    private static final String CUSTOMER_SQL_INSERT = "INSERT INTO CUSTOMER (NAME, STATUS) VALUES (?, ?)";
    private static final String CUSTOMER_SQL_INSERT_GENERATED_KEY = "id";
    private static final String CUSTOMER_SQL_SELECT_ALL = "SELECT * FROM CUSTOMER";
    private static final String CUSTOMER_SQL_SELECT_BY_ID = "SELECT * FROM CUSTOMER WHERE ID = ?";

    private JdbcTemplate jdbcTemplateObject;
    PreparedStatementCreatorFactory psCreatorFactory;

    public void setDataSource(DataSource dataSource) {
        // init JdbcTemplate
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
        // init PreparedStatementCreatorFactory
        psCreatorFactory = new PreparedStatementCreatorFactory(CUSTOMER_SQL_INSERT, Types.VARCHAR, Types.INTEGER);
        psCreatorFactory.setReturnGeneratedKeys(true);
        psCreatorFactory.setGeneratedKeysColumnNames(CUSTOMER_SQL_INSERT_GENERATED_KEY);
    }

    /**
     * @param customer customer data to insert
     * @return generated customer id on success or -1 on failure
     */
    @Override
    public long add(final Customer customer) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        PreparedStatementCreator psc =
                psCreatorFactory.newPreparedStatementCreator(new Object[]{customer.getName(), customer.getStatus()});
        int result = jdbcTemplateObject.update(psc, keyHolder);

        return result > 0 ? keyHolder.getKey().longValue() : -1;
    }

    @Override
    public void add(List<Customer> customers) {
        List<Object[]> batchArgs = new ArrayList<Object[]>();
        for (Customer customer : customers) {
            batchArgs.add(new Object[]{customer.getName(), customer.getStatus()});
        }
        jdbcTemplateObject.batchUpdate(CUSTOMER_SQL_INSERT, batchArgs);
    }

    @Override
    public Customer getById(int customerId) {
        return jdbcTemplateObject.queryForObject(CUSTOMER_SQL_SELECT_BY_ID, new CustomerRowMapper(), customerId);
    }

    @Override
    public List<Customer> getAll() {
        return jdbcTemplateObject.query(CUSTOMER_SQL_SELECT_ALL, new CustomerRowMapper());
    }
}
