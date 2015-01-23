package com.scm.dao;

import com.scm.model.Customer;

import java.util.List;

/**
 * Created by yholub on 1/22/2015.
 */
public interface CustomerDAO {
    void add(Customer customer);
    void add(List<Customer> customers);
    Customer getById(int customerId);
    List<Customer> getAll();
}
