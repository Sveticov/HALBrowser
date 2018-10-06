package com.svetikov.demo000.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Customer {
    private long id;
    private String name;
    private String lname;
}
