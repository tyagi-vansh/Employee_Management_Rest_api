package com.example.employee.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Token {

    @Id
    private String email;
    private String token;
}
