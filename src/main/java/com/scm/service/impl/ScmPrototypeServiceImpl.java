package com.scm.service.impl;

import com.scm.dao.CustomerDAO;
import com.scm.model.Customer;
import com.scm.service.ScmPrototypeService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Serhii_Bondarenko on 1/23/2015.
 */
public class ScmPrototypeServiceImpl implements ScmPrototypeService {

    private CustomerDAO costumerDao;

    public ScmPrototypeServiceImpl(CustomerDAO costumerDao) {
        this.costumerDao = costumerDao;
    }

    @Override
    public void add(List<List<String>> csvData) {
        List<Customer> customers = new ArrayList<>();
        for (List<String> line : csvData) {
            Customer customer = new Customer(line.get(0), Integer.valueOf(line.get(1)));
        }

        costumerDao.add(customers);
    }
}
