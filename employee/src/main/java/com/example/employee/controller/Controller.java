package com.example.employee.controller;
import com.example.employee.*;

import com.example.employee.entity.Employee;
import com.example.employee.request.LoginRequest;
import com.example.employee.request.Request;
import com.example.employee.request.UpdatePassword;
import com.example.employee.request.UpdateProfile;
import com.example.employee.response.Message;
import com.example.employee.response.Response;
import com.example.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;

@RestController
@RequestMapping("/employee")
public class Controller {
    @Autowired
    private EmployeeService employeeService;


    @PostMapping("/registration")
    public ResponseEntity<Message> addNewEmployee(@RequestBody Request request,@RequestHeader("Token") String token) {
        return employeeService.addNewEmployee(request,token);
    }
    @GetMapping("/getProfile/{id}")
    public Response viewEmployee(@PathVariable int id,@RequestHeader("Token") String token) {
        return employeeService.viewemployee(id,token);
    }
    @PostMapping("/login")
    public ResponseEntity<Message> login(@RequestBody LoginRequest loginRequest) {
        return employeeService.login(loginRequest);
    }
    @PostMapping("/updateProfile")
    public ResponseEntity<Message> upProfile(@RequestBody UpdateProfile updateprofile,@RequestHeader("Token") String token){
        return employeeService.upProfile(updateprofile,token);
    }
    @PostMapping("/resetPassword")
    public ResponseEntity<Message> upPassword(@RequestBody UpdatePassword updatepass,@RequestHeader("Token") String token){
        return employeeService.upPassword(updatepass,token);
    }
    @PostMapping("/logout")
    public ResponseEntity<Message> UserLogout(@RequestHeader String token){
        return employeeService.logoutUser(token);
    }
}
