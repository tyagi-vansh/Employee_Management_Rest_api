package com.example.employee.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Employees")
public class Employee {
    @Id
    @GeneratedValue
    private int id ;
    private String department;
    private String name;
    private String email;
    private String mobile ;
    private String password;
    private int age;
    private String creationDate;
}
