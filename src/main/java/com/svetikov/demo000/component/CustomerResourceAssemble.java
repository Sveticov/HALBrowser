package com.svetikov.demo000.component;

import com.svetikov.demo000.controller.MyController;
import com.svetikov.demo000.model.Customer;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.net.URI;

@Component
public class CustomerResourceAssemble implements ResourceAssembler<Customer, Resource<Customer>> {

    @Override
    public Resource<Customer> toResource(Customer customer) {
        Resource<Customer> customerResource=new Resource<>(customer);
        URI selfURI= null;
        try {
            selfURI = MvcUriComponentsBuilder
                    .fromMethodCall(
                            MvcUriComponentsBuilder.on(MyController.class).get(
                                    customer.getId()
                            )
                    ).buildAndExpand().toUri();
        } catch (Exception e) {
            e.printStackTrace();
        }

        customerResource.add(new Link(selfURI.toString(),"self"));
        return customerResource;
    }
}
