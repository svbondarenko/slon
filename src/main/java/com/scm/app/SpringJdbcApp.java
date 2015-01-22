package com.scm.app;

import com.scm.dao.CustomerDAO;
import com.scm.model.Customer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by yholub on 1/22/2015.
 */
public class SpringJdbcApp {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");

        CustomerDAO customerDAO = (CustomerDAO) context.getBean("customerDAO");
        Customer customer1 = new Customer(1, "BBC", 11);
        Customer customer3 = new Customer(2, "CNN", 22);
        Customer customer2 = new Customer(3, "TSN", 33);

        customerDAO.add(customer1);
        customerDAO.add(customer2);
        customerDAO.add(customer3);

        Customer customerA = customerDAO.getById(1);
        System.out.println("Customer A : " + customerA);

        Customer customerB = customerDAO.getById(2);
        System.out.println("Customer B : " + customerB);

        Customer customerC = customerDAO.getById(3);
        System.out.println("Customer C : " + customerC);
    }
}
