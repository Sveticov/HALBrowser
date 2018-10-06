package com.svetikov.demo000.service;

import com.svetikov.demo000.model.Customer;

import javax.xml.ws.ServiceMode;
import java.util.List;
import java.util.Optional;

public interface RepositoriCustomer {
    List<Customer> findAll();

    void save(Customer customer);

    Optional<Customer> findById(long id);
}
