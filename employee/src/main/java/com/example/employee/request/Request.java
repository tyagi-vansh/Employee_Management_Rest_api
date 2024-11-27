package com.example.employee.request;

import lombok.Data;


@Data
public class Request {

    private String department;
    private String name;
    private String email;
    private String mobile ;
    private String password;
    private int age;

}
