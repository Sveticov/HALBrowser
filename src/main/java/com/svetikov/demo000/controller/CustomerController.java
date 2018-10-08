package com.svetikov.demo000.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomerController {

    @GetMapping("/customer/page")
    public String cust_page(){
        return "access_customer";
    }
}
