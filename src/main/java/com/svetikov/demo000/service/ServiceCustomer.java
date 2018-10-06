package com.svetikov.demo000.service;

import com.svetikov.demo000.model.Customer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class ServiceCustomer implements RepositoriCustomer {
    List<Customer> customers=new ArrayList<>();

    @Override
    public List<Customer> findAll() {
        return customers.stream().collect(Collectors.toList());
    }

    @Override
    public void save(Customer customer) {
        customers.add(customer);
    }

    @Override
    public Optional<Customer> findById(long id) {
        return this.customers.stream()
                .filter(customer -> customer.getId()==id)
               .findFirst();
    }
}
