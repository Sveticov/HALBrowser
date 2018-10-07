package com.svetikov.demo000.controller;

import com.svetikov.demo000.component.CustomerResourceAssemble;
import com.svetikov.demo000.model.Customer;
import com.svetikov.demo000.service.RepositoriCustomer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/ua", produces = "application/hal+json")
@Slf4j
public class MyController {
    private final RepositoriCustomer repositoriCustomer;
    private final CustomerResourceAssemble customerResourceAssemble;

    @Autowired
    MyController(RepositoriCustomer repositoriCustomer,CustomerResourceAssemble customerResourceAssemble) {
        this.repositoriCustomer = repositoriCustomer;
        this.customerResourceAssemble=customerResourceAssemble;
    }

    @GetMapping
    public ResponseEntity<Resources<Object>> root() {
        Resources<Object> objects = new Resources<>(Collections.emptyList());

        URI uri = MvcUriComponentsBuilder
                .fromMethodCall(MvcUriComponentsBuilder.on(getClass()).getCollection())
                .build().toUri();
        log.info("uri: " + uri);
        Link link = new Link(uri.toString(), "test");
        objects.add(link);
        return ResponseEntity.ok(objects);
    }

    @GetMapping("/customers")
    ResponseEntity<Resources<Resource<Customer>>> getCollection() {
        List<Resource<Customer>> collect = this.repositoriCustomer.findAll()
                .stream()
                .map(customerResourceAssemble::toResource)
                .collect(Collectors.<Resource<Customer>>toList());

        Resources<Resource<Customer>> resources=new Resources<>(collect);
        URI self= ServletUriComponentsBuilder
                .fromCurrentRequest().build().toUri();
        resources.add(new Link(self.toString(),"self"));
        return ResponseEntity.ok(resources);

    }

    @GetMapping("customer/{id}")
    public ResponseEntity<Resource<Customer>> get(@PathVariable Long id) throws Exception {
        return this.repositoriCustomer.findById(id)
                .map(customer -> ResponseEntity.ok(this.customerResourceAssemble.toResource(customer)))
                .orElseThrow(()->new Exception());
    }

    @RequestMapping(value = "/customers",method = RequestMethod.OPTIONS)
    public ResponseEntity<?> options(){
        return ResponseEntity.ok()
                .allow(HttpMethod.GET,HttpMethod.POST,HttpMethod.HEAD,
                        HttpMethod.OPTIONS,HttpMethod.DELETE,HttpMethod.PUT)
                .build();
    }
}
