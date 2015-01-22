package com.scm.dao;

import com.scm.model.Customer;

/**
 * Created by yholub on 1/22/2015.
 */
public interface CustomerDAO {
    public void add(Customer customer);
    public Customer getById(int customerId);
}
