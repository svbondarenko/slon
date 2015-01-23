package com.scm.model;

/**
 * Created by yholub on 1/22/2015.
 */
public class Customer {

    private  int id;
    private final String name;
    private final int status;

    public Customer(String name, int status) {
        this.name = name;
        this.status = status;
    }

    public Customer(int id, String name, int status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                '}';
    }

}
