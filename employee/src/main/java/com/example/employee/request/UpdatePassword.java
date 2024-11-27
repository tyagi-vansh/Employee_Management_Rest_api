package com.example.employee.request;

import lombok.Data;

@Data
public class UpdatePassword {
    private String email;
    private String oldpassword;
    private String newpassword;

}
