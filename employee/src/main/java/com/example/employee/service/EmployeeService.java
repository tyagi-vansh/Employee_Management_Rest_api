package com.example.employee.service;

import com.example.employee.dao.EmployeeRepository;
import com.example.employee.dao.TokenRepository;
import com.example.employee.entity.Employee;
import com.example.employee.entity.Token;
import com.example.employee.request.LoginRequest;
import com.example.employee.request.Request;
import com.example.employee.request.UpdatePassword;
import com.example.employee.request.UpdateProfile;
import com.example.employee.response.Message;
import com.example.employee.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class EmployeeService implements Services {
    @Autowired
    private EmployeeRepository emp;

    @Autowired
    private TokenRepository tokenRepository;

    public ResponseEntity<Message> addNewEmployee(Request request,String token) {

        Message message= new Message();
        Employee e = emp.findByEmail(request.getEmail());
        if (e != null) {
            message.setMessage("Email already exists");
            return ResponseEntity.ok(message);
        }
        Employee employee = new Employee();
        employee.setEmail(request.getEmail());
        employee.setName(request.getName());
        employee.setDepartment(request.getDepartment());
        employee.setAge(request.getAge());
        employee.setMobile(request.getMobile());
        employee.setPassword(request.getPassword());
        Date d = new Date();
        employee.setCreationDate(""+d);
        emp.save(employee);
        message.setMessage("Employee registration successfully completed");
        return ResponseEntity.ok(message);
    }

    public Response viewemployee(int id,String token) {
        Token vToken = tokenRepository.findByToken(token);
        String email = vToken.getEmail();
        Employee e = emp.findById(id);
        if(e == null) {
            return null;
        }
        if(e.getEmail().equals(email)) {
            Response res = new Response();
            res.setName(e.getName());
            res.setAge(e.getAge());
            res.setDepartment(e.getDepartment());
            res.setEmail(e.getEmail());
            res.setMobile(e.getMobile());
            return res;
        }
        return null;
    }

    public ResponseEntity<Message> login(LoginRequest loginRequest) {
        Employee employee = emp.findByEmail(loginRequest.getEmail());
        Message message = new Message();
        System.out.println(employee);
        if(employee != null && employee.getPassword().equals(loginRequest.getPassword())) {
            String random= UUID.randomUUID().toString();
            Token token = new Token();
            token.setToken(random);
            token.setEmail(employee.getEmail());
            tokenRepository.save(token);
            message.setMessage("token: "+ random);
            return ResponseEntity.ok(message);
        }
        message.setMessage("invalid id aur password please try again");
        return ResponseEntity.ok(message);
    }
    public ResponseEntity<Message> upProfile(UpdateProfile updateprofile,String token){
        Token vToken = tokenRepository.findByToken(token);
        String email=vToken.getEmail();
        Message message = new Message();
        Employee employee = emp.findByEmail(updateprofile.getEmail());
        if(!updateprofile.getEmail().equals(email)){
            message.setMessage("you dont have access to this facility");
            return ResponseEntity.ok(message);
        }
        if(employee!=null) {
            employee.setName(updateprofile.getName());
            employee.setAge(updateprofile.getAge());
            employee.setDepartment(updateprofile.getDepartment());
            employee.setMobile(updateprofile.getMobile());
            emp.save(employee);
            message.setMessage("Employee update successfull");
            return ResponseEntity.ok(message);
        }
        message.setMessage("Try Again");
        return ResponseEntity.ok(message);
    }

    public ResponseEntity<Message> upPassword(UpdatePassword updatepass,String token){
        Token vToken = tokenRepository.findByToken(token);
        String email = vToken.getEmail();

        Employee employee = emp.findByEmail(updatepass.getEmail());
        Message message = new Message();
        if(employee != null && updatepass.getEmail().equals(email)) {
            if(employee.getPassword().equals(updatepass.getOldpassword())) {
                employee.setPassword(updatepass.getNewpassword());
                message.setMessage("Password updated successfully");
                emp.save(employee);
                return ResponseEntity.ok(message);
            }
            message.setMessage("invalid password or Invalid Access token");
            return ResponseEntity.ok(message);
        }
        message.setMessage("employee doest not exist");
        return ResponseEntity.ok(message);

    }
    public ResponseEntity<Message> logoutUser(String token){
        Token vToken = tokenRepository.findByToken(token);
        String email = vToken.getEmail();
        tokenRepository.delete(vToken);
        Message message = new Message();
        message.setMessage("Logged Out successfully");
        return ResponseEntity.ok(message);
    }

}

