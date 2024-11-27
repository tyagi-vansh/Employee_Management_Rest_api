package com.example.employee.service;

import com.example.employee.entity.Employee;
import com.example.employee.request.Request;
import com.example.employee.request.UpdatePassword;
import com.example.employee.request.UpdateProfile;
import com.example.employee.response.Message;
import com.example.employee.response.Response;
import org.springframework.http.ResponseEntity;

public interface Services {
    public ResponseEntity<Message> addNewEmployee(Request request,String token);

    public Response viewemployee(int id,String token);

    public ResponseEntity<Message> upProfile(UpdateProfile updateprofile,String token);

    public ResponseEntity<Message> upPassword(UpdatePassword updatepass,String token);

    public ResponseEntity<Message> logoutUser(String token);
}

