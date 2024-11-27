package com.example.employee.request;

import lombok.Data;

@Data
public class UpdateProfile {
    private String department;
    private String name;
    private String email;
    private String mobile ;
    private int age;

}
