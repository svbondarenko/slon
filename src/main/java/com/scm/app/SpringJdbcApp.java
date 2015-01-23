package com.scm.app;

import com.scm.dao.CustomerDAO;
import com.scm.model.Customer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yholub on 1/22/2015.
 */
public class SpringJdbcApp {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        CustomerDAO customerDAO = (CustomerDAO) context.getBean("customerDAO");

        // simple insert

        Customer customer1 = new Customer(1, "BBC", 11);
        customerDAO.add(customer1);

        // batch insert

        Customer customer2 = new Customer(2, "CNN", 22);
        Customer customer3 = new Customer(3, "TSN", 33);
        Customer customer4 = new Customer(4, "TSN", 44);

        List<Customer> customers = new ArrayList<Customer>();

        customers.add(customer2);
        customers.add(customer3);
        customers.add(customer4);

        customerDAO.add(customers);

        // select all

        List<Customer> all = customerDAO.getAll();
        for (Customer customer : all) {
            System.out.println(customer);
        }
    }
}
