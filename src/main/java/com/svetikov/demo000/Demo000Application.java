package com.svetikov.demo000;

import com.svetikov.demo000.model.Customer;
import com.svetikov.demo000.service.RepositoriCustomer;
import com.svetikov.demo000.service.ServiceCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Demo000Application implements CommandLineRunner {
@Autowired
    RepositoriCustomer repositoriCustomer;
    public static void main(String[] args) {
        SpringApplication.run(Demo000Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Customer customer1=new Customer(1,"Danil","Svetikov");
        Customer customer2=new Customer(2,"Lena","Svetikov");
        Customer customer3=new Customer(3,"Dilana","Svetikov");

        repositoriCustomer.save(customer1);
        repositoriCustomer.save(customer2);
        repositoriCustomer.save(customer3);
    }
}
