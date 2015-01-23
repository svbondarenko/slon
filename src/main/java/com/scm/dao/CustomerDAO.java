package com.scm.dao;

import com.scm.model.Customer;

import java.util.List;

/**
 * Created by yholub on 1/22/2015.
 */
public interface CustomerDAO {

    // add Customer
    void add(Customer customer);

    // add list of Customer
    void add(List<Customer> customers);

    // select Customer by id
    Customer getById(int customerId);

    // select all
    List<Customer> getAll();
}
